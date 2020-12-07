package org.firespoon.fsbotclient.function

import org.firespoon.fsbotclient.command.SimpleCommand
import org.firespoon.fsbotclient.function.env.arknights.ArkNightsCalcEnv
import org.firespoon.fsbotclient.service.ArkNightsService
import org.firespoon.fsbotclient.utils.NetworkUtils

class ArkNightsFunction {
    companion object {
        private val service = NetworkUtils.buildService(ArkNightsService::class)

        val calcCommand = SimpleCommand(
            keywords = listOf(".ancalc"),
            factory = { ArkNightsCalcEnv() },
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
