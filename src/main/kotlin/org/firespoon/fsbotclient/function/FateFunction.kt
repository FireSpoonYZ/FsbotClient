package org.firespoon.fsbotclient.function

import org.firespoon.fsbotclient.cli.*
import org.firespoon.fsbotclient.command.SimpleCommand
import org.firespoon.fsbotclient.service.FateService
import org.firespoon.fsbotclient.service.ServantService
import org.firespoon.fsbotclient.utils.NetworkUtils

abstract class FateFunction {
    companion object {
        private val fateService = NetworkUtils.buildService(FateService::class)
        private val servantService = NetworkUtils.buildService(ServantService::class)

        class FateFateEnv : EmptyCli() {
            val time: Int? by int().nullable()
        }
        val fateCommand = SimpleCommand(
            keywords = listOf(".fate"),
            envClazz = FateFateEnv::class,
            getResult = { fateService.fate(time) },
            getMessage = { fateResultList ->
                fateResultList.joinToString(
                    prefix = "您的fate人物做成结果为：\n",
                    separator = "\n"
                )
            }
        )

        class FateRandomServantEnv : EmptyCli() {
            val userId: Long by long()
            val clazz: String? by clazz().nullable()
            val time: Int? by int().nullable()
            val command: String? by string().nullable()
        }
        val randomServantCommand = SimpleCommand(
            keywords = listOf(".svt", ".servant"),
            envClazz = FateRandomServantEnv::class,
            getResult = { servantService.random(time, clazz, userId, command) },
            getMessage = { servantList ->
                servantList.joinToString(
                    prefix = "您的从者为：\n",
                    separator = "\n"
                )
            },
            prefixArgs = { event ->
                val res = mutableListOf<String>()
                res.add(event.sender.id.toString())
                res
            }
        )

        class FateAddServantEnv : EmptyCli() {
            val clazz: String by clazz()
            val name: String by string()
        }
        val addServantCommand = SimpleCommand(
            keywords = listOf(".asvt", ".add_servant"),
            envClazz = FateAddServantEnv::class,
            getResult = { servantService.save(name, clazz) },
            getMessage = { count ->
                if (count > 0) {
                    "保存成功"
                } else {
                    "保存失败"
                }
            }
        )

        class FateDeleteServantEnv : EmptyCli() {
            val clazz: String by clazz()
            val name: String by string()
        }
        val deleteServantCommand = SimpleCommand(
            keywords = listOf(".dsvt", ".delete_servant"),
            envClazz = FateDeleteServantEnv::class,
            getResult = { servantService.delete(name, clazz) },
            getMessage = { count ->
                if (count > 0) {
                    "删除成功"
                } else {
                    "删除失败"
                }
            }
        )

        class FateRandomHassanEnv : EmptyCli() {
            val time : Int? by int().nullable()
        }
        val randomHassanCommand = SimpleCommand(
            listOf(".has", ".hassan"),
            envClazz = FateRandomHassanEnv::class,
            getResult = { servantService.randomHassan(time) },
            getMessage = { servantList ->
                servantList.joinToString(
                    prefix = "您的从者为：\n",
                    separator = "\n"
                )
            }
        )
    }
}
