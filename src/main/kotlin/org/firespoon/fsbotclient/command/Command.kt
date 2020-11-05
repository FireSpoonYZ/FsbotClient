package org.firespoon.fsbotclient.command

import net.mamoe.mirai.message.MessageEvent
import net.mamoe.mirai.message.data.content
import org.firespoon.fsbotclient.cli.BaseCli
import kotlin.reflect.KClass

abstract class FsCommand<C : BaseCli, E : MessageEvent>(
    val eventClass : KClass<E>,
    val keywords: List<String>,
    val factory: () -> C,
    private val prefixArgs: ArgParser<E> = { emptyList() },
    private val messageArgs: ArgParser<E> = { event ->
        val content = event.message.content
        val allTokens = content.split(Regex("\\s+"))
        allTokens.subList(1, allTokens.size)
    },
    private val suffixArgs: ArgParser<E> = { emptyList() }
) {
    protected var event: E? = null

    private fun getArgs(event: E): List<String> {
        val args = mutableListOf<String>()
        args.addAll(prefixArgs(event))
        args.addAll(messageArgs(event))
        args.addAll(suffixArgs(event))
        return args
    }

    abstract suspend fun run(env: C)

    open suspend fun execute(event: E) {
        this.event = event
        val args = getArgs(event)
        val env = factory()
        try {
            env.call(args)
        } catch (e: IllegalArgumentException) {
            if (e.message != null) {
                event.reply(e.message!!)
            }
        }
        run(env)
    }
}

typealias ArgParser<E> = (E) -> List<String>
