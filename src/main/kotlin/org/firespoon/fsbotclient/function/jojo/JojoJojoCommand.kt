package org.firespoon.fsbotclient.function.jojo

import net.mamoe.mirai.message.MessageEvent
import org.firespoon.fsbotclient.cli.EmptyCli
import org.firespoon.fsbotclient.cli.int
import org.firespoon.fsbotclient.cli.nullable
import org.firespoon.fsbotclient.command.FsCommand
import org.firespoon.fsbotclient.command.annotation.Keywords
import org.firespoon.fsbotclient.model.FsResult
import org.firespoon.fsbotclient.model.JojoResult

@Keywords([".jojo"])
class JojoJojoCommand : FsCommand<List<JojoResult>, MessageEvent>() {
    val time : Int? by int().nullable()

    override fun result(): FsResult<List<JojoResult>> {
        return JojoFunction.jojoService.jojo(time)
    }

    override fun message(result: List<JojoResult>): String {
        return result.joinToString(
            prefix = "您的替身面板为：\n",
            separator = "\n"
        ) {
            val sb = StringBuilder()
            sb.append("力量：${it.power} ")
            sb.append("速度：${it.speed} ")
            sb.append("射程：${it.range} ")
            sb.append("持久：${it.lasting} ")
            sb.append("精密：${it.precision} ")
            sb.append("成长：${it.growth} ")
            sb.append("能力：${it.ability}")
            sb.toString()
        }
    }
}
