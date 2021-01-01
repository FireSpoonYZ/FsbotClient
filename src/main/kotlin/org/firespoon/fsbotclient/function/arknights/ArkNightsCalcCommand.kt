package org.firespoon.fsbotclient.function.arknights

import net.mamoe.mirai.message.MessageEvent
import org.firespoon.fsbotclient.cli.*
import org.firespoon.fsbotclient.command.FsCommand
import org.firespoon.fsbotclient.command.annotation.Keywords
import org.firespoon.fsbotclient.model.Agent
import org.firespoon.fsbotclient.model.FsResult
import org.firespoon.fsbotclient.model.Tag

@Keywords([".ancalc"])
class ArkNightsCalcCommand : FsCommand<List<Pair<List<Tag>, List<Agent>>>, MessageEvent>() {
    val tag1: Tag by tag()
    val tag2: Tag by tag()
    val tag3: Tag by tag()
    val tag4: Tag by tag()
    val tag5: Tag by tag()

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
