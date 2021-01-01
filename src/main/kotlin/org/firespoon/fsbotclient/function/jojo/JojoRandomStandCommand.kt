package org.firespoon.fsbotclient.function.jojo

import net.mamoe.mirai.message.MessageEvent
import org.firespoon.fsbotclient.cli.int
import org.firespoon.fsbotclient.cli.nullable
import org.firespoon.fsbotclient.command.FsCommand
import org.firespoon.fsbotclient.command.annotation.Keywords
import org.firespoon.fsbotclient.model.FsResult
import org.firespoon.fsbotclient.model.Stand

@Keywords([".rsd", ".random_stand"])
class JojoRandomStandCommand : FsCommand<List<Stand>, MessageEvent>() {
    val time : Int? by int().nullable()

    override fun result(): FsResult<List<Stand>> {
        return JojoFunction.standService.random(time)
    }

    override fun message(result: List<Stand>): String {
        return result.joinToString(
            separator = "\n\n",
            prefix = "您的替身为\n"
        ) {
            val sb = StringBuilder()
            it.apply {
                val name = when {
                    englishName == null -> name
                    name == englishName -> name
                    else -> "$name（$englishName）"
                }
                sb.append("替身名：$name\n")
                sb.append("力量：$power\n")
                sb.append("速度：$speed\n")
                sb.append("射程：$range\n")
                sb.append("持久：$lasting\n")
                sb.append("精密：$precision\n")
                sb.append("成长：$growth\n")
                sb.append("能力：$ability")
            }
            sb.toString()
        }
    }
}
