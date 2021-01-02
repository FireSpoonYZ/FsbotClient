package org.firespoon.fsbotclient.function.coc

import net.mamoe.mirai.message.MessageEvent
import org.firespoon.fsbotclient.cli.int
import org.firespoon.fsbotclient.cli.nullable
import org.firespoon.fsbotclient.command.FsCommand
import org.firespoon.fsbotclient.command.annotation.Doc
import org.firespoon.fsbotclient.command.annotation.Keywords
import org.firespoon.fsbotclient.model.CocResult
import org.firespoon.fsbotclient.model.FsResult

@Keywords([".coc"])
@Doc("Coc人物做成")
class CocCocCommand : FsCommand<List<CocResult>, MessageEvent>() {
    private val time: Int? by int().nullable()

    override fun result(): FsResult<List<CocResult>> {
        return CocFunction.cocService.coc(time)
    }

    override fun message(result: List<CocResult>): String {
        return result.joinToString(
            prefix = "您的coc人物做成结果为:\n",
            separator = "\n"
        ) {
            var res: String
            it.apply {
                val total = STR!! + CON!! + SIZ!! + DEX!! + APP!! + POW!! + EDU!!
                val totalWithLUK = total + LUK!!
                res =
                    "力量:$STR 体质:$CON 体型:$SIZ 敏捷:$DEX 外貌:$APP 智力:$INT 意志:$POW 教育:$EDU 幸运:$LUK 总计:$total/$totalWithLUK"
            }
            res
        }
    }
}