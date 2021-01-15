package org.firespoon.fsbotclient.function.dna

import org.firespoon.fsbotclient.command.FsCommand
import org.firespoon.fsbotclient.command.annotation.Doc
import org.firespoon.fsbotclient.command.annotation.Keywords
import org.firespoon.fsbotclient.model.Dna
import org.firespoon.fsbotclient.model.FsResult

@Keywords([".dna"])
@Doc("随机抽取Dna")
class DnaDnaCommand : FsCommand<List<Dna>>() {
    override fun result(): FsResult<List<Dna>> {
        return DnaFunction.dnaService.random()
    }

    override fun message(result: List<Dna>): String {
        return result.sortedBy { it.rank }.joinToString(
            prefix = "您的随机dna结果为：\n",
            separator = "\n"
        ) {
            "${"☆".repeat(it.rank!!)} ${it.name!!}"
        }
    }
}
