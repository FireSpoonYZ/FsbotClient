package org.firespoon.fsbotclient.function.coc

import net.mamoe.mirai.event.events.MessageEvent
import org.firespoon.fsbotclient.cli.long
import org.firespoon.fsbotclient.cli.string
import org.firespoon.fsbotclient.command.FsCommand
import org.firespoon.fsbotclient.command.annotation.Doc
import org.firespoon.fsbotclient.command.annotation.Keywords
import org.firespoon.fsbotclient.model.FsResult

@Keywords([".dcd", ".delete_card"])
@Doc("删除角色卡")
class CocDeleteCardCommand : FsCommand<Int>() {
    val ownerId: Long by long()
    val name: String by string("角色卡的名字")

    override fun prefixArgs(event: MessageEvent): List<String> {
        val res = mutableListOf<String>()
        res.add(event.sender.id.toString())
        return res
    }

    override fun result(): FsResult<Int> {
        return CocFunction.cardService.delete(ownerId, name)
    }

    override fun message(result: Int): String {
        return if (result > 0) {
            "删除成功"
        } else {
            "删除失败"
        }
    }
}
