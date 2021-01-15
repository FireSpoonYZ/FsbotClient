package org.firespoon.fsbotclient.command

import net.mamoe.mirai.event.events.MessageEvent
import net.mamoe.mirai.message.data.content
import org.firespoon.fsbotclient.cli.EmptyCli
import org.firespoon.fsbotclient.model.FsResult

abstract class FsCommand<T> : ICommand, EmptyCli() {
    open fun prefixArgs(event: MessageEvent): List<String> {
        return emptyList()
    }

    open fun args(event: MessageEvent): List<String> {
        val content = event.message.content
        val allTokens = content.split(Regex("\\s+"))
        return allTokens.subList(1, allTokens.size)
    }

    open fun suffixArgs(event: MessageEvent): List<String> {
        return emptyList()
    }

    fun init(event : MessageEvent) {
        val args = mutableListOf<String>()
        args.addAll(prefixArgs(event))
        args.addAll(args(event))
        args.addAll(suffixArgs(event))

        this.parse(args)
    }

    abstract fun result(): FsResult<T>

    abstract fun message(result: T): String

    override fun getMessage(event : MessageEvent): String {
        this.init(event)
        val result = this.result()
        return if (result.code == 200) {
            this.message(result.data!!)
        } else {
            result.message!!
        }
    }
}
