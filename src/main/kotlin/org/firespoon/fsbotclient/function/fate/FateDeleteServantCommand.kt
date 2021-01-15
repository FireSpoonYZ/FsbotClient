package org.firespoon.fsbotclient.function.fate

import org.firespoon.fsbotclient.cli.*
import org.firespoon.fsbotclient.command.FsCommand
import org.firespoon.fsbotclient.command.annotation.Doc
import org.firespoon.fsbotclient.command.annotation.Keywords
import org.firespoon.fsbotclient.model.FsResult

@Keywords([".dsvt", ".delete_servant"])
@Doc("删除从者")
class FateDeleteServantCommand : FsCommand<Int>() {
    val clazz: String by clazz("职阶")
    val name: String by string("名字")

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
