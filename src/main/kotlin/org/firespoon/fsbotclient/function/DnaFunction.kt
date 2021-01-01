package org.firespoon.fsbotclient.function

import org.firespoon.fsbotclient.service.DnaService
import org.firespoon.fsbotclient.cli.EmptyCli
import org.firespoon.fsbotclient.command.SimpleCommand
import org.firespoon.fsbotclient.utils.NetworkUtils

abstract class DnaFunction {
    companion object {
        private val dnaService = NetworkUtils.buildService(DnaService::class)

        val randomDnaCommand = SimpleCommand(
            keywords = listOf(".dna"),
            envClazz = EmptyCli::class,
            getResult = { dnaService.random() },
            getMessage = { dnaList ->
                val sortedDnaList = dnaList.sortedBy { it.rank }
                sortedDnaList.joinToString(
                    prefix = "您的随机dna结果为：\n",
                    separator = "\n"
                )
            }
        )
    }
}
