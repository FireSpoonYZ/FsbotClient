package org.firespoon.fsbotclient.function.coc

import org.firespoon.fsbotclient.command.resources.MessageEvent
import org.firespoon.fsbotclient.cli.long
import org.firespoon.fsbotclient.command.mirai.FsCommand
import org.firespoon.fsbotclient.command.mirai.annotation.Doc
import org.firespoon.fsbotclient.command.mirai.annotation.Keywords
import org.firespoon.fsbotclient.model.FsResult

@Keywords([".ccd", ".clear_card"])
@Doc("清空当前角色卡")
class CocClearCardCommand : FsCommand<Int>() {
    val placeId: Long by long()
    val ownerId: Long by long()

    override fun prefixArgs(event: MessageEvent): List<String> {
        val res = mutableListOf<String>()
        res.add(event.subject.id.toString())
        res.add(event.sender.id.toString())
        return res
    }

    override fun result(): FsResult<Int> {
        return CocFunction.cocService.init(placeId, ownerId)
    }

    override fun message(result: Int): String {
        return if (result > 0) {
            "初始化成功"
        } else {
            "初始化失败"
        }
    }
}
