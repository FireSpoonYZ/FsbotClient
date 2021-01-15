package org.firespoon.fsbotclient.function.coc

import net.mamoe.mirai.event.events.MessageEvent
import org.firespoon.fsbotclient.cli.*
import org.firespoon.fsbotclient.command.FsCommand
import org.firespoon.fsbotclient.command.annotation.Doc
import org.firespoon.fsbotclient.command.annotation.Keywords
import org.firespoon.fsbotclient.model.FsResult

@Keywords([".st"])
@Doc("设置技能值")
class CocSetCommand : FsCommand<Int>()  {
    val placeId: Long by long()
    val ownerId: Long by long()
    val name: String by string("技能名")
    val value: Int by int("技能值")

    override fun prefixArgs(event: MessageEvent): List<String> {
        val res = mutableListOf<String>()
        res.add(event.subject.id.toString())
        res.add(event.sender.id.toString())
        return res
    }

    override fun result(): FsResult<Int> {
        return CocFunction.cocService.setProperty(placeId, ownerId, name, value)
    }

    override fun message(result: Int): String {
        return if (result > 0) {
            "设置成功"
        } else {
            "设置失败"
        }
    }
}
