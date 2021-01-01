package org.firespoon.fsbotclient.command

import net.mamoe.mirai.message.MessageEvent
import net.mamoe.mirai.message.data.content
import org.firespoon.fsbotclient.cli.EmptyCli
import org.firespoon.fsbotclient.model.FsResult

abstract class FsCommand<T, M : MessageEvent> : ICommand<M>, EmptyCli() {
    open fun prefixArgs(event: M): List<String> {
        return emptyList()
    }

    open fun args(event: M): List<String> {
        val content = event.message.content
        val allTokens = content.split(Regex("\\s+"))
        return allTokens.subList(1, allTokens.size)
    }

    open fun suffixArgs(event: M): List<String> {
        return emptyList()
    }

    fun init(event : M) {
        val args = mutableListOf<String>()
        args.addAll(prefixArgs(event))
        args.addAll(args(event))
        args.addAll(suffixArgs(event))

        this.parse(args)
    }

    abstract fun result(): FsResult<T>

    abstract fun message(result: T): String

    override fun getMessage(event : M): String {
        this.init(event)
        val result = this.result()
        return if (result.code == 200) {
            this.message(result.data!!)
        } else {
            result.message!!
        }
    }
}
