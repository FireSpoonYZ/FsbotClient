package org.firespoon.fsbotclient.function.fate

import org.firespoon.fsbotclient.cli.*
import org.firespoon.fsbotclient.command.FsCommand
import org.firespoon.fsbotclient.command.annotation.Doc
import org.firespoon.fsbotclient.command.annotation.Keywords
import org.firespoon.fsbotclient.model.FsResult
import org.firespoon.fsbotclient.model.Servant

@Keywords([".has", ".hassan"])
@Doc("随机抽取哈桑")
class FateRandomHassanCommand : FsCommand<List<Servant>>() {
    val time : Int by int("次数").default(1)

    override fun result(): FsResult<List<Servant>> {
        return FateFunction.servantService.randomHassan(time)
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
