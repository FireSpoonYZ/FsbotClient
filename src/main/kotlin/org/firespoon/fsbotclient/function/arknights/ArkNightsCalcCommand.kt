package org.firespoon.fsbotclient.function.arknights

import org.firespoon.fsbotclient.cli.*
import org.firespoon.fsbotclient.command.mirai.FsCommand
import org.firespoon.fsbotclient.command.mirai.annotation.Doc
import org.firespoon.fsbotclient.command.mirai.annotation.Keywords
import org.firespoon.fsbotclient.model.Agent
import org.firespoon.fsbotclient.model.FsResult
import org.firespoon.fsbotclient.model.Tag

@Keywords([".ancalc"])
@Doc("公开招募计算器")
class ArkNightsCalcCommand : FsCommand<List<Pair<List<Tag>, List<Agent>>>>() {
    val tag1: Tag by tag("标签1")
    val tag2: Tag by tag("标签2")
    val tag3: Tag by tag("标签3")
    val tag4: Tag by tag("标签4")
    val tag5: Tag by tag("标签5")

    override fun result(): FsResult<List<Pair<List<Tag>, List<Agent>>>> {
        return ArkNightsFunction.service.calculate(tag1, tag2, tag3, tag4, tag5)
    }

    override fun message(result: List<Pair<List<Tag>, List<Agent>>>): String {
        return result.joinToString(
            prefix = "您的公开招募计算结果为：\n",
            separator = "\n"
        ) {
            val tagsStr = it.first.joinToString(separator = " ")
            val agentsStr = it.second.joinToString(separator = " ") { agent ->
                agent.name!!
            }
            String.format("%s => %s", tagsStr, agentsStr)
        }
    }
}
