package org.firespoon.fsbotclient.function.coc

import org.firespoon.fsbotclient.command.resources.MessageEvent
import org.firespoon.fsbotclient.cli.*
import org.firespoon.fsbotclient.command.mirai.FsCommand
import org.firespoon.fsbotclient.command.mirai.annotation.Doc
import org.firespoon.fsbotclient.command.mirai.annotation.Keywords
import org.firespoon.fsbotclient.model.CheckResult
import org.firespoon.fsbotclient.model.FsResult

@Keywords([".ra", ".rc"])
@Doc("检定")
class CocCheckCommand : FsCommand<CheckResult>() {
    private val placeId: Long by long()
    private val ownerId: Long by long()
    val name: String by string("进行检定的技能名")
    private val value: Int? by int("指定的技能值").nullable()

    override fun prefixArgs(event: MessageEvent): List<String> {
        val res = mutableListOf<String>()
        res.add(event.subject.id.toString())
        res.add(event.sender.id.toString())
        return res
    }

    override fun result(): FsResult<CheckResult> {
        return CocFunction.cocService.check(placeId, ownerId, name, value)
    }

    override fun message(result: CheckResult): String {
        val property = result.property!!
        val diceResult = result.diceResult!!.res!!
        val finalResult = result.result
        return "您的 $name 检定结果为: $diceResult/$property $finalResult"
    }
}
