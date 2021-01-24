package org.firespoon.fsbotclient.function.record

import org.firespoon.fsbotclient.cli.long
import org.firespoon.fsbotclient.cli.string
import org.firespoon.fsbotclient.command.mirai.FsCommand
import org.firespoon.fsbotclient.command.resources.MessageEvent
import org.firespoon.fsbotclient.command.mirai.annotation.Doc
import org.firespoon.fsbotclient.command.mirai.annotation.Keywords
import org.firespoon.fsbotclient.model.FsResult

@Keywords([".drd", "delete_record"])
@Doc("删除一条备忘录")
class RecordDeleteCommand : FsCommand<String>() {
    val ownerId: Long by long()
    val key: String by string("关键字")

    override fun prefixArgs(event: MessageEvent): List<String> {
        val res = mutableListOf<String>()
        res.add(event.sender.id.toString())
        return res
    }

    override fun result(): FsResult<String> {
        return RecordFunction.recordService.delete(ownerId, key)
    }

    override fun message(result: String): String {
        return result
    }
}
