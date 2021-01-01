package org.firespoon.fsbotclient.function

import org.firespoon.fsbotclient.cli.EmptyCli
import org.firespoon.fsbotclient.cli.int
import org.firespoon.fsbotclient.cli.nullable
import org.firespoon.fsbotclient.command.SimpleCommand
import org.firespoon.fsbotclient.service.JojoService
import org.firespoon.fsbotclient.service.StandService
import org.firespoon.fsbotclient.utils.NetworkUtils

abstract class JojoFunction {
    companion object {
        private val jojoService = NetworkUtils.buildService(JojoService::class)
        private val standService = NetworkUtils.buildService(StandService::class)

        class JojoJojoEnv : EmptyCli() {
            val time : Int? by int().nullable()
        }
        val jojoCommand = SimpleCommand(
            keywords = listOf(".jojo"),
            envClazz = JojoJojoEnv::class,
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

        class JojoRandomStandEnv : EmptyCli() {
            val time : Int? by int().nullable()
        }
        val randomStandCommand = SimpleCommand(
            listOf(".rsd", ".random_stand"),
            envClazz = JojoRandomStandEnv::class,
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
