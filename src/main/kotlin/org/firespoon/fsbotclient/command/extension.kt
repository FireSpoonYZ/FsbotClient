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
import kotlin.reflect.KClass
import kotlin.reflect.full.*

inline fun <reified M : MessageEvent> Bot.register(clazz: KClass<out ICommand<M>>) {
    val keywords = clazz.findAnnotation<Keywords>()!!.keywords
    subscribeAlways(M::class) { event ->
        val content = event.message.content
        val keyword = content.split(Regex("\\s+"))[0]
        if (keywords.contains(keyword)) {
            try {
                val inst = clazz.createInstance()
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
                event.reply(e.message!!)
            }
        }
    }
}
