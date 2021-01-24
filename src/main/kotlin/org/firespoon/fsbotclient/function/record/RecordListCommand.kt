package org.firespoon.fsbotclient.function.record

import org.firespoon.fsbotclient.cli.long
import org.firespoon.fsbotclient.command.mirai.FsCommand
import org.firespoon.fsbotclient.command.mirai.annotation.Doc
import org.firespoon.fsbotclient.command.mirai.annotation.Keywords
import org.firespoon.fsbotclient.command.resources.MessageEvent
import org.firespoon.fsbotclient.model.FsResult
import org.firespoon.fsbotclient.model.Record

@Keywords([".lrd", "list_record"])
@Doc("查看所有备忘录")
class RecordListCommand : FsCommand<List<Record>>() {
    val ownerId: Long by long()

    override fun prefixArgs(event: MessageEvent): List<String> {
        val res = mutableListOf<String>()
        res.add(event.sender.id.toString())
        return res
    }

    override fun result(): FsResult<List<Record>> {
        return RecordFunction.recordService.list(ownerId)
    }

    override fun message(result: List<Record>): String {
        return result.joinToString(
            prefix = "您的备忘录为：\n",
            separator = "\n"
        ) {
            "${it.key} ${it.value}"
        }
    }
}