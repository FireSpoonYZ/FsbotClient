package org.firespoon.fsbotclient.function.record

import org.firespoon.fsbotclient.cli.long
import org.firespoon.fsbotclient.cli.string
import org.firespoon.fsbotclient.command.mirai.FsCommand
import org.firespoon.fsbotclient.command.mirai.annotation.Doc
import org.firespoon.fsbotclient.command.mirai.annotation.Keywords
import org.firespoon.fsbotclient.command.resources.MessageEvent
import org.firespoon.fsbotclient.model.FsResult

@Keywords([".srd", ".set_record"])
@Doc("保存一条备忘录")
class RecordSaveCommand : FsCommand<String>() {
    val ownerId: Long by long()
    val key: String by string("关键字")
    val value: String by string("文字")

    override fun prefixArgs(event: MessageEvent): List<String> {
        val res = mutableListOf<String>()
        res.add(event.sender.id.toString())
        return res
    }

    override fun result(): FsResult<String> {
        return RecordFunction.recordService.save(ownerId, key, value)
    }

    override fun message(result: String): String {
        return result
    }
}
