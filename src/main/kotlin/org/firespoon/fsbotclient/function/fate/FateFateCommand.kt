package org.firespoon.fsbotclient.function.fate

import net.mamoe.mirai.message.MessageEvent
import org.firespoon.fsbotclient.cli.default
import org.firespoon.fsbotclient.cli.int
import org.firespoon.fsbotclient.cli.nullable
import org.firespoon.fsbotclient.command.FsCommand
import org.firespoon.fsbotclient.command.annotation.Doc
import org.firespoon.fsbotclient.command.annotation.Keywords
import org.firespoon.fsbotclient.model.FateResult
import org.firespoon.fsbotclient.model.FsResult

@Keywords([".fate"])
@Doc("Fate人物做成")
class FateFateCommand : FsCommand<List<FateResult>, MessageEvent>() {
    val time : Int by int("次数").default(1)

    override fun result(): FsResult<List<FateResult>> {
        return FateFunction.fateService.fate(time)
    }

    override fun message(result: List<FateResult>): String {
        return result.joinToString(
            prefix = "您的fate人物做成结果为：\n",
            separator = "\n"
        ) {
            val sb = StringBuilder()
            sb.append("魔力:${it.magicPowerLevel} ")
            sb.append("魔术回路:${it.magicCircuitLevel} ")
            sb.append("属性:${it.attributes!!.joinToString(",")} ")
            sb.append("起源:${it.origin} ")
            sb.append("魔力量:${it.magicPower} ")
            sb.append("出身:${it.birth}")
            sb.toString()
        }
    }
}
