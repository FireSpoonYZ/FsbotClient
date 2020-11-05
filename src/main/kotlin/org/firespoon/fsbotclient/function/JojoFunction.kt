package org.firespoon.fsbotclient.function

import org.firespoon.fsbotclient.command.SimpleCommand
import org.firespoon.fsbotclient.function.env.jojo.JojoJojoEnv
import org.firespoon.fsbotclient.function.env.jojo.JojoRandomStandEnv
import org.firespoon.fsbotclient.service.JojoService
import org.firespoon.fsbotclient.service.StandService
import org.firespoon.fsbotclient.utils.NetworkUtils

abstract class JojoFunction {
    companion object {
        private val jojoService = NetworkUtils.buildService(JojoService::class)
        private val standService = NetworkUtils.buildService(StandService::class)

        val jojoCommand = SimpleCommand(
            keywords = listOf(".jojo"),
            factory = { JojoJojoEnv() },
            getResult = { jojoService.jojo(time) },
            getMessage = { jojoResultList ->
                val sb = StringBuilder("您的替身面板为：")
                val jojoResultListMsg = jojoResultList.joinToString(
                    separator = "\n",
                    prefix = "\n"
                )
                sb.append(jojoResultListMsg)
                sb.toString()
            }
        )

        val randomStandCommand = SimpleCommand(
            listOf(".rsd", ".random_stand"),
            factory = { JojoRandomStandEnv() },
            getResult = { standService.random(time) },
            getMessage = { standList ->
                val sb = StringBuilder("您的替身为：")
                val standListMsg = standList.joinToString(
                    separator = "\n\n",
                    prefix = "\n"
                )
                sb.append(standListMsg)
                sb.toString()
            }
        )
    }
}
