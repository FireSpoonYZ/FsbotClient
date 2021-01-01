package org.firespoon.fsbotclient.function.coc

import net.mamoe.mirai.message.MessageEvent
import org.firespoon.fsbotclient.cli.*
import org.firespoon.fsbotclient.command.FsCommand
import org.firespoon.fsbotclient.command.annotation.Keywords
import org.firespoon.fsbotclient.model.FsResult

@Keywords([".st"])
class CocSetCommand : FsCommand<Int, MessageEvent>()  {
    val placeId: Long by long()
    val ownerId: Long by long()
    val name: String by string()
    val value: Int by int()

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
