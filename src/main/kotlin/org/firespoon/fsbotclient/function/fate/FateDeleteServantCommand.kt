package org.firespoon.fsbotclient.function.fate

import net.mamoe.mirai.message.MessageEvent
import org.firespoon.fsbotclient.cli.*
import org.firespoon.fsbotclient.command.FsCommand
import org.firespoon.fsbotclient.command.annotation.Keywords
import org.firespoon.fsbotclient.model.FsResult

@Keywords([".dsvt", ".delete_servant"])
class FateDeleteServantCommand : FsCommand<Int, MessageEvent>() {
    val clazz: String by clazz()
    val name: String by string()

    override fun result(): FsResult<Int> {
        return FateFunction.servantService.delete(name, clazz)
    }

    override fun message(result: Int): String {
        return if (result > 0) {
            "删除成功"
        } else {
            "删除失败"
        }
    }
}
