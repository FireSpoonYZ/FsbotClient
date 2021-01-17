package org.firespoon.fsbotclient.command.mirai

import net.mamoe.mirai.Bot
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.events.MessageEvent
import net.mamoe.mirai.message.data.MessageChainBuilder
import net.mamoe.mirai.message.data.QuoteReply
import net.mamoe.mirai.message.data.content
import org.firespoon.fsbotclient.command.mirai.annotation.Keywords
import org.firespoon.fsbotclient.command.mirai.resources.MessageEventImpl
import org.firespoon.fsbotclient.function.HelpCommand
import kotlin.reflect.KClass
import kotlin.reflect.full.*

fun <T> Bot.register(clazz: KClass<out FsCommand<T>>) {
    val keywords = clazz.findAnnotation<Keywords>()!!.keywords

    HelpCommand.registerCommand(clazz)

    eventChannel.subscribeAlways(MessageEvent::class) { event ->
        val content = event.message.content
        val keyword = content.split(Regex("\\s+"))[0]
        if (keywords.contains(keyword)) {
            try {
                val inst = clazz.primaryConstructor!!.call()
                val mEvent = MessageEventImpl(event)
                val message = inst.getMessage(mEvent)

                val mb = MessageChainBuilder()
                if (event is GroupMessageEvent) {
                    val qr = QuoteReply(event.message)
                    mb.add(qr)
                }
                mb.add(message)
                event.subject.sendMessage(mb.build())
            } catch (e : Exception) {
                val sb = StringBuilder(e.javaClass.simpleName)
                if (e.message != null) {
                    sb.append(": ")
                    sb.append(e.message)
                }
                event.subject.sendMessage(sb.toString())
                e.printStackTrace()
            }
        }
    }
}
