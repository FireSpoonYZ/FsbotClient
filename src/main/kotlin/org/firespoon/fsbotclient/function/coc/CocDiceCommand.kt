package org.firespoon.fsbotclient.function.coc

import net.mamoe.mirai.message.MessageEvent
import org.firespoon.fsbotclient.cli.default
import org.firespoon.fsbotclient.cli.string
import org.firespoon.fsbotclient.command.FsCommand
import org.firespoon.fsbotclient.command.annotation.Keywords
import org.firespoon.fsbotclient.model.DiceResult
import org.firespoon.fsbotclient.model.FsResult

@Keywords([".r", ".rd"])
class CocDiceCommand : FsCommand<DiceResult, MessageEvent>()  {
    val diceExp: String by string().default("1d100")

    override fun result(): FsResult<DiceResult> {
        return CocFunction.diceService.dice(diceExp)
    }

    override fun message(result: DiceResult): String {
        return "您的骰点结果为:${result.process}"
    }
}
