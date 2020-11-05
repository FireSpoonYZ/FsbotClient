package org.firespoon.fsbotclient.function

import net.mamoe.mirai.message.MessageEvent
import org.firespoon.fsbotclient.service.CardService
import org.firespoon.fsbotclient.service.CocService
import org.firespoon.fsbotclient.service.DiceService
import org.firespoon.fsbotclient.command.SimpleCommand
import org.firespoon.fsbotclient.function.env.coc.*
import org.firespoon.fsbotclient.utils.NetworkUtils
import java.lang.StringBuilder

abstract class CocFunction {
    companion object {
        private val diceService = NetworkUtils.buildService(DiceService::class)
        private val cocService = NetworkUtils.buildService(CocService::class)
        private val cardService = NetworkUtils.buildService(CardService::class)

        val diceCommand = SimpleCommand(
            keywords = listOf(".r", ".rd"),
            factory = { CocDiceEnv() },
            getResult = { diceService.dice(diceExp) },
            getMessage = { data ->
                "您的骰点结果为：${data.process}"
            }
        )

        val checkCommand = SimpleCommand(
            keywords = listOf(".ra", ".rc"),
            factory = { CocCheckEnv() },
            getResult = { cocService.check(placeId, ownerId, name, value) },
            getMessage = { checkResult ->
                val property = checkResult.property!!
                val diceResult = checkResult.diceResult!!.res!!
                val finalResult = checkResult.result
                "您的 $name 检定结果为： $diceResult/$property $finalResult"
            },
            prefixArgs = { event ->
                val res = mutableListOf<String>()
                res.add(event.subject.id.toString())
                res.add(event.sender.id.toString())
                res
            }
        )

        val setCommand = SimpleCommand(
            keywords = listOf(".st"),
            factory = { CocSetEnv() },
            getResult = { cocService.setProperty(placeId, ownerId, name, value) },
            getMessage = { count ->
                if (count > 0) {
                    "设置成功"
                } else {
                    "设置失败"
                }
            },
            prefixArgs = { event: MessageEvent ->
                val res = mutableListOf<String>()
                res.add(event.subject.id.toString())
                res.add(event.sender.id.toString())
                res
            }
        )

        val saveCardCommand = SimpleCommand(
            keywords = listOf(".scd", ".save_card"),
            factory = { CocSaveCardEnv() },
            getResult = { cocService.saveCard(placeId, ownerId, name) },
            getMessage = { count ->
                if (count > 0) {
                    "保存成功"
                } else {
                    "保存失败"
                }
            },
            prefixArgs = { event: MessageEvent ->
                val res = mutableListOf<String>()
                res.add(event.subject.id.toString())
                res.add(event.sender.id.toString())
                res
            }
        )

        val loadCardCommand = SimpleCommand(
            keywords = listOf(".lcd", ".load_card"),
            factory = { CocLoadCardEnv() },
            getResult = { cocService.loadCard(placeId, ownerId, name) },
            getMessage = { count ->
                if (count > 0) {
                    "加载成功"
                } else {
                    "加载失败"
                }
            },
            prefixArgs = { event: MessageEvent ->
                val res = mutableListOf<String>()
                res.add(event.subject.id.toString())
                res.add(event.sender.id.toString())
                res
            }
        )

        val clearCardCommand = SimpleCommand(
            keywords = listOf(".ccd", ".clear_card"),
            factory = { CocClearCardEnv() },
            getResult = { cocService.init(placeId, ownerId) },
            getMessage = { count ->
                if (count > 0) {
                    "初始化成功"
                } else {
                    "初始化失败"
                }
            },
            prefixArgs = { event: MessageEvent ->
                val res = mutableListOf<String>()
                res.add(event.subject.id.toString())
                res.add(event.sender.id.toString())
                res
            }
        )

        val deleteCardCommand = SimpleCommand(
            keywords = listOf(".dcd", ".delete_card"),
            factory = { CocDeleteCardEnv() },
            getResult = { cardService.delete(ownerId, name) },
            getMessage = { count ->
                if (count > 0) {
                    "删除成功"
                } else {
                    "删除失败"
                }
            },
            prefixArgs = { event: MessageEvent ->
                val res = mutableListOf<String>()
                res.add(event.sender.id.toString())
                res
            }
        )

        val allCardCommand = SimpleCommand(
            keywords = listOf(".acd", ".all_card"),
            factory = { CocAllCardEnv() },
            getResult = { cardService.getAll(ownerId) },
            getMessage = { cardList ->
                val sb = StringBuilder("您的角色卡为:")
                val cardListStr = cardList.joinToString(
                    separator = "\n",
                    prefix = "\n"
                ) { it.name!! }
                sb.append(cardListStr)
                sb.toString()
            },
            prefixArgs = { event: MessageEvent ->
                val res = mutableListOf<String>()
                res.add(event.sender.id.toString())
                res
            }
        )
    }
}
