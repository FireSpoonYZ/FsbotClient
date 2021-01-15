package org.firespoon.fsbotclient.function.fate

import org.firespoon.fsbotclient.cli.*
import org.firespoon.fsbotclient.command.FsCommand
import org.firespoon.fsbotclient.command.annotation.Doc
import org.firespoon.fsbotclient.command.annotation.Keywords
import org.firespoon.fsbotclient.model.FsResult

@Keywords([".asvt", ".add_servant"])
@Doc("增加新从者")
class FateAddServantCommand : FsCommand<Int>() {
    val clazz: String by clazz("职阶")
    val name: String by string("名字")

    override fun result(): FsResult<Int> {
        var name = this.name
        if (!name.endsWith("[diy]")) {
            name += "[diy]"
        }
        return FateFunction.servantService.save(name, clazz)
    }

    override fun message(result: Int): String {
        return if (result > 0) {
            "保存成功"
        } else {
            "保存失败"
        }
    }
}
