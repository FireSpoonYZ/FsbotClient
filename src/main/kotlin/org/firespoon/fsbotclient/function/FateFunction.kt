package org.firespoon.fsbotclient.function

import org.firespoon.fsbotclient.command.SimpleCommand
import org.firespoon.fsbotclient.function.env.fate.FateAddServantEnv
import org.firespoon.fsbotclient.function.env.fate.FateFateEnv
import org.firespoon.fsbotclient.function.env.fate.FateRandomHassanEnv
import org.firespoon.fsbotclient.function.env.fate.FateRandomServantEnv
import org.firespoon.fsbotclient.service.FateService
import org.firespoon.fsbotclient.service.ServantService
import org.firespoon.fsbotclient.utils.NetworkUtils

abstract class FateFunction {
    companion object {
        private val fateService = NetworkUtils.buildService(FateService::class)
        private val servantService = NetworkUtils.buildService(ServantService::class)

        val fateCommand = SimpleCommand(
            keywords = listOf(".fate"),
            factory = { FateFateEnv() },
            getResult = { fateService.fate(time) },
            getMessage = { fateResultList ->
                val sb = StringBuilder("您的fate人物做成结果为：\n")
                val fateResultMsg = fateResultList.joinToString(separator = "\n")
                sb.append(fateResultMsg)
                sb.toString()
            }
        )

        val randomServantCommand = SimpleCommand(
            keywords = listOf(".svt", ".servant"),
            factory = { FateRandomServantEnv() },
            getResult = { servantService.random(time, clazz, userId, command) },
            getMessage = { servantList ->
                val sb = java.lang.StringBuilder("您的从者为：")
                val servantListMsg = servantList.joinToString(
                    separator = "\n",
                    prefix = "\n"
                )
                sb.append(servantListMsg)
                sb.toString()
            },
            prefixArgs = { event ->
                val res = mutableListOf<String>()
                res.add(event.sender.id.toString())
                res
            }
        )

        val addServantCommand = SimpleCommand(
            keywords = listOf(".asvt", ".add_servant"),
            factory = { FateAddServantEnv() },
            getResult = { servantService.save(name, clazz) },
            getMessage = { count ->
                if (count > 0) {
                    "保存成功"
                } else {
                    "保存失败"
                }
            }
        )

        val deleteServantCommand = SimpleCommand(
            keywords = listOf(".dsvt", ".delete_servant"),
            factory = { FateAddServantEnv() },
            getResult = { servantService.delete(name, clazz) },
            getMessage = { count ->
                if (count > 0) {
                    "删除成功"
                } else {
                    "删除失败"
                }
            }
        )

        val randomHassanCommand = SimpleCommand(
            listOf(".has", ".hassan"),
            factory = { FateRandomHassanEnv() },
            getResult = { servantService.randomHassan(time) },
            getMessage = { servantList ->
                val sb = java.lang.StringBuilder("您的从者为：")
                val servantListMsg = servantList.joinToString(
                    separator = "\n",
                    prefix = "\n"
                )
                sb.append(servantListMsg)
                sb.toString()
            }
        )
    }
}
