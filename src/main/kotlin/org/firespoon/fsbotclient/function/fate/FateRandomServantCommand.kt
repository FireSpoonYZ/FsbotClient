package org.firespoon.fsbotclient.function.fate

import net.mamoe.mirai.message.MessageEvent
import org.firespoon.fsbotclient.cli.*
import org.firespoon.fsbotclient.command.FsCommand
import org.firespoon.fsbotclient.command.annotation.Doc
import org.firespoon.fsbotclient.command.annotation.Keywords
import org.firespoon.fsbotclient.model.FsResult
import org.firespoon.fsbotclient.model.Servant

@Keywords([".svt", ".servant"])
@Doc("召唤从者")
class FateRandomServantCommand : FsCommand<List<Servant>, MessageEvent>() {
    val userId: Long by long()
    val clazz: String? by clazz("职阶").nullable()
    val time: Int? by int("次数").nullable()
    val command: String? by string("召唤咒语").nullable()

    override fun prefixArgs(event: MessageEvent): List<String> {
        val res = mutableListOf<String>()
        res.add(event.sender.id.toString())
        return res
    }

    override fun result(): FsResult<List<Servant>> {
        return FateFunction.servantService.random(time, clazz, userId, command)
    }

    override fun message(result: List<Servant>): String {
        return result.joinToString(
            prefix = "您的从者为：\n",
            separator = "\n"
        ) {
            "${it.clazz} ${it.name}"
        }
    }
}
