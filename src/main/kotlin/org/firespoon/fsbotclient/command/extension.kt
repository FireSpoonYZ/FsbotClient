package org.firespoon.fsbotclient.command

import net.mamoe.mirai.Bot
import net.mamoe.mirai.event.subscribeAlways
import net.mamoe.mirai.message.MessageEvent
import net.mamoe.mirai.message.data.content
import org.firespoon.fsbotclient.cli.BaseCli
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

fun <C: BaseCli, E : MessageEvent>
        Bot.register(command : FsCommand<C, E>) {
    subscribeAlways(command.eventClass) { event ->
        val content = event.message.content
        val keyword = content.split(Regex("\\s+"))[0]
        if (command.keywords.contains(keyword)) {
            command.execute(event)
        }
    }
}
