package org.firespoon.fsbotclient.function

import net.mamoe.mirai.message.MessageEvent
import org.firespoon.fsbotclient.cli.*
import org.firespoon.fsbotclient.service.CardService
import org.firespoon.fsbotclient.service.CocService
import org.firespoon.fsbotclient.service.DiceService
import org.firespoon.fsbotclient.command.SimpleCommand
import org.firespoon.fsbotclient.utils.NetworkUtils
import java.lang.StringBuilder

abstract class CocFunction {
    companion object {
        private val diceService = NetworkUtils.buildService(DiceService::class)
        private val cocService = NetworkUtils.buildService(CocService::class)
        private val cardService = NetworkUtils.buildService(CardService::class)

        class CocCocEnv : EmptyCli() {
            val time: Int? by int().nullable()
        }
        val cocCommand = SimpleCommand(
            keywords = listOf(".coc"),
            envClazz = CocCocEnv::class,
            getResult = { cocService.coc(time) },
            getMessage = { data ->
                List(data.size) { i ->
                    var res: String
                    data[i].apply {
                        val total = STR!! + CON!! + SIZ!! + DEX!! + APP!! + POW!! + EDU!!
                        res =
                            "力量:$STR 体质:$CON 体型:$SIZ 敏捷:$DEX 外貌:$APP 智力:$INT 意志:$POW 教育:$EDU 幸运:$LUK 总计:$total/${total + LUK!!}"
                    }
                    res
                }.joinToString(prefix = "您的coc人物做成结果为:\n", separator = "\n")
            }
        )

        class CocDiceEnv : EmptyCli() {
            val diceExp: String by string().default("1d100")
        }
        val diceCommand = SimpleCommand(
            keywords = listOf(".r", ".rd"),
            envClazz = CocDiceEnv::class,
            getResult = { diceService.dice(diceExp) },
            getMessage = { data ->
                "您的骰点结果为:${data.process}"
            }
        )

        class CocCheckEnv : EmptyCli() {
            val placeId: Long by long()
            val ownerId: Long by long()
            val name: String by string()
            val value: Int? by int().nullable()
        }
        val checkCommand = SimpleCommand(
            keywords = listOf(".ra", ".rc"),
            envClazz = CocCheckEnv::class,
            getResult = { cocService.check(placeId, ownerId, name, value) },
            getMessage = { checkResult ->
                val property = checkResult.property!!
                val diceResult = checkResult.diceResult!!.res!!
                val finalResult = checkResult.result
                "您的 $name 检定结果为: $diceResult/$property $finalResult"
            },
            prefixArgs = { event ->
                val res = mutableListOf<String>()
                res.add(event.subject.id.toString())
                res.add(event.sender.id.toString())
                res
            }
        )

        class CocSetEnv : EmptyCli() {
            val placeId: Long by long()
            val ownerId: Long by long()
            val name: String by string()
            val value: Int by int()
        }
        val setCommand = SimpleCommand(
            keywords = listOf(".st"),
            envClazz = CocSetEnv::class,
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

        class CocSaveCardEnv : EmptyCli() {
            val placeId: Long by long()
            val ownerId: Long by long()
            val name: String by string()
        }
        val saveCardCommand = SimpleCommand(
            keywords = listOf(".scd", ".save_card"),
            envClazz = CocSaveCardEnv::class,
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

        class CocLoadCardEnv : EmptyCli() {
            val placeId: Long by long()
            val ownerId: Long by long()
            val name: String by string()
        }
        val loadCardCommand = SimpleCommand(
            keywords = listOf(".lcd", ".load_card"),
            envClazz = CocLoadCardEnv::class,
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

        class CocClearCardEnv : EmptyCli() {
            val placeId: Long by long()
            val ownerId: Long by long()
        }
        val clearCardCommand = SimpleCommand(
            keywords = listOf(".ccd", ".clear_card"),
            envClazz = CocClearCardEnv::class,
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

        class CocDeleteCardEnv : EmptyCli() {
            val ownerId: Long by long()
            val name: String by string()
        }
        val deleteCardCommand = SimpleCommand(
            keywords = listOf(".dcd", ".delete_card"),
            envClazz = CocDeleteCardEnv::class,
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

        class CocAllCardEnv : EmptyCli() {
            val ownerId: Long by long()
        }
        val allCardCommand = SimpleCommand(
            keywords = listOf(".acd", ".all_card"),
            envClazz = CocAllCardEnv::class,
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
