package org.firespoon.fsbotclient.command

import net.mamoe.mirai.Bot
import net.mamoe.mirai.event.subscribeAlways
import net.mamoe.mirai.message.GroupMessageEvent
import net.mamoe.mirai.message.MessageEvent
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.MessageChainBuilder
import net.mamoe.mirai.message.data.content
import org.firespoon.fsbotclient.cli.BaseCli
import org.firespoon.fsbotclient.command.annotation.Keywords
import org.firespoon.fsbotclient.function.CommandClass
import org.firespoon.fsbotclient.function.HelpCommand
import kotlin.reflect.KClass
import kotlin.reflect.full.*

inline fun <T, reified M : MessageEvent> Bot.register(clazz: KClass<out FsCommand<T, M>>) {
    val keywords = clazz.findAnnotation<Keywords>()!!.keywords

    HelpCommand.registerCommand(clazz)

    subscribeAlways(M::class) { event ->
        val content = event.message.content
        val keyword = content.split(Regex("\\s+"))[0]
        if (keywords.contains(keyword)) {
            try {
                val inst = clazz.primaryConstructor!!.call()
                val message = clazz.functions.firstOrNull { it.name == "getMessage" }!!.call(inst, event)

                val mb = MessageChainBuilder()
                if (event is GroupMessageEvent) {
                    val sender = (event as GroupMessageEvent).sender
                    val at = At(sender)
                    mb.add(at)
                    mb.add(" ")
                }
                mb.add(message as String)
                event.reply(mb.build())
            } catch (e : Exception) {
                //event.reply(e.message?: "未知错误")
                e.printStackTrace()
            }
        }
    }
}
