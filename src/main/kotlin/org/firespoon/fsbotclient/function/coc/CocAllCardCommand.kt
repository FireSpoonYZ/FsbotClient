package org.firespoon.fsbotclient.function.coc

import net.mamoe.mirai.message.MessageEvent
import org.firespoon.fsbotclient.cli.long
import org.firespoon.fsbotclient.command.FsCommand
import org.firespoon.fsbotclient.command.annotation.Keywords
import org.firespoon.fsbotclient.model.Card
import org.firespoon.fsbotclient.model.FsResult

@Keywords([".acd", ".all_card"])
class CocAllCardCommand : FsCommand<List<Card>, MessageEvent>() {
    val ownerId: Long by long()

    override fun prefixArgs(event: MessageEvent): List<String> {
        val res = mutableListOf<String>()
        res.add(event.sender.id.toString())
        return res
    }

    override fun result(): FsResult<List<Card>> {
        return CocFunction.cardService.getAll(ownerId)
    }

    override fun message(result: List<Card>): String {
        return result.joinToString(
            prefix = "您的角色卡为:\n",
            separator = "\n"
        ) { it.name!! }
    }
}