package org.firespoon.fsbotclient.function.jojo

import org.firespoon.fsbotclient.cli.default
import org.firespoon.fsbotclient.cli.int
import org.firespoon.fsbotclient.command.mirai.FsCommand
import org.firespoon.fsbotclient.command.mirai.annotation.Doc
import org.firespoon.fsbotclient.command.mirai.annotation.Keywords
import org.firespoon.fsbotclient.model.FsResult
import org.firespoon.fsbotclient.model.Stand

@Keywords([".rsd", ".random_stand"])
@Doc("随机抽取替身")
class JojoRandomStandCommand : FsCommand<List<Stand>>() {
    val time : Int by int("次数").default(1)

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
