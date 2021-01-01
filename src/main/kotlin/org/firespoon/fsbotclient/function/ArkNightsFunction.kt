package org.firespoon.fsbotclient.function

import org.firespoon.fsbotclient.cli.EmptyCli
import org.firespoon.fsbotclient.cli.tag
import org.firespoon.fsbotclient.command.SimpleCommand
import org.firespoon.fsbotclient.model.Tag
import org.firespoon.fsbotclient.service.ArkNightsService
import org.firespoon.fsbotclient.utils.NetworkUtils

class ArkNightsFunction {
    companion object {
        private val service = NetworkUtils.buildService(ArkNightsService::class)

        class ArkNightsCalcEnv : EmptyCli() {
            val tag1: Tag by tag()
            val tag2: Tag by tag()
            val tag3: Tag by tag()
            val tag4: Tag by tag()
            val tag5: Tag by tag()
        }
        val calcCommand = SimpleCommand(
            keywords = listOf(".ancalc"),
            envClazz = ArkNightsCalcEnv::class,
            getResult = { service.calculate(tag1, tag2, tag3, tag4, tag5) },
            getMessage = { result ->
                val sb = StringBuilder("您的公开招募计算结果为：\n")

                val resultMsg = result.joinToString(separator = "\n") { it ->
                    val tagsStr = it.first.joinToString(separator = " ")
                    val agentsStr = it.second.joinToString(separator = " ") { agent ->
                        agent.name!!
                    }
                    String.format("%s => %s", tagsStr, agentsStr)
                }
                sb.append(resultMsg)
                sb.toString()
            }
        )
    }
}
