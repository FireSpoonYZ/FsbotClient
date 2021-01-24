package org.firespoon.fsbotclient.function.coc

import org.firespoon.fsbotclient.cli.default
import org.firespoon.fsbotclient.cli.string
import org.firespoon.fsbotclient.command.mirai.FsCommand
import org.firespoon.fsbotclient.command.mirai.annotation.Doc
import org.firespoon.fsbotclient.command.mirai.annotation.Keywords
import org.firespoon.fsbotclient.model.DiceResult
import org.firespoon.fsbotclient.model.FsResult

@Keywords([".r", ".rd"])
@Doc("掷骰")
class CocDiceCommand : FsCommand<DiceResult>()  {
    val diceExp: String by string("表达式").default("1d100")

    override fun result(): FsResult<DiceResult> {
        return CocFunction.diceService.dice(diceExp)
    }

    override fun message(result: DiceResult): String {
        return "您的骰点结果为:${result.process}"
    }
}
