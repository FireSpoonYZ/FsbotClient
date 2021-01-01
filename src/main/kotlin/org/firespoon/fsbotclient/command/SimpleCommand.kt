package org.firespoon.fsbotclient.command

import net.mamoe.mirai.message.GroupMessageEvent
import net.mamoe.mirai.message.MessageEvent
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.MessageChainBuilder
import net.mamoe.mirai.message.data.content
import org.firespoon.fsbotclient.cli.BaseCli
import org.firespoon.fsbotclient.model.FsResult
import kotlin.reflect.KClass

class SimpleCommand<C : BaseCli, D>(
    keywords: List<String>,
    envClazz : KClass<C>,
    val getResult: C.() -> FsResult<D>?,
    val getMessage: C.(D) -> String,
    prefixArgs: ArgParser<MessageEvent> = { emptyList() },
    messageArgs: ArgParser<MessageEvent> = { event ->
        val content = event.message.content
        val allTokens = content.split(Regex("\\s+"))
        allTokens.subList(1, allTokens.size)
    },
    suffixArgs: ArgParser<MessageEvent> = { emptyList() }
) : FsCommand<C, MessageEvent>(
    MessageEvent::class, keywords, envClazz, prefixArgs, messageArgs, suffixArgs
) {
    override suspend fun run(env: C) {
        require(event != null) {
            "Event is null."
        }
        var msg : String? = null
        env.apply {
            val res = this.getResult()
            if (res != null) {
                if (res.code == 200) {
                    val data = res.data
                    if (data != null) {
                        msg = this.getMessage(data)
                    }
                } else {
                    msg = res.message!!
                }
            }
        }

        if (msg != null) {
            val mb = MessageChainBuilder()
            if (event is GroupMessageEvent) {
                val sender = (event as GroupMessageEvent).sender
                val at = At(sender)
                mb.add(at)
            }
            mb.add(msg!!)
            event!!.reply(mb.build())
        }
    }
}
