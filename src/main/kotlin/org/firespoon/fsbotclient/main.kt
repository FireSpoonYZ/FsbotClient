package org.firespoon.fsbotclient

import net.mamoe.mirai.BotFactory.INSTANCE.newBot
import net.mamoe.mirai.alsoLogin
import org.firespoon.fsbotclient.command.mirai.register
import org.firespoon.fsbotclient.function.HelpCommand
import org.firespoon.fsbotclient.function.coc.*
import org.firespoon.fsbotclient.function.fate.*
import org.firespoon.fsbotclient.function.dna.*
import org.firespoon.fsbotclient.function.jojo.*
import org.firespoon.fsbotclient.function.arknights.*
import org.firespoon.fsbotclient.function.record.RecordDeleteCommand
import org.firespoon.fsbotclient.function.record.RecordListCommand
import org.firespoon.fsbotclient.function.record.RecordLoadCommand
import org.firespoon.fsbotclient.function.record.RecordSaveCommand

suspend fun main() {
    val qqId = 744821060L
    val password = "1!2@3#skyYZ"

    val bot = newBot(qqId, password) {
        fileBasedDeviceInfo()
    }

    bot.also {
        it.register(HelpCommand::class)

        it.register(CocCocCommand::class)
        it.register(CocDiceCommand::class)
        it.register(CocSetCommand::class)
        it.register(CocCheckCommand::class)
        it.register(CocSaveCardCommand::class)
        it.register(CocLoadCardCommand::class)
        it.register(CocClearCardCommand::class)
        it.register(CocDeleteCardCommand::class)
        it.register(CocAllCardCommand::class)

        it.register(DnaDnaCommand::class)

        it.register(FateFateCommand::class)
        it.register(FateRandomServantCommand::class)
        it.register(FateAddServantCommand::class)
        it.register(FateDeleteServantCommand::class)
        it.register(FateRandomHassanCommand::class)

        it.register(JojoJojoCommand::class)
        it.register(JojoRandomStandCommand::class)

        it.register(ArkNightsCalcCommand::class)

        it.register(RecordSaveCommand::class)
        it.register(RecordLoadCommand::class)
        it.register(RecordListCommand::class)
        it.register(RecordDeleteCommand::class)

        it.alsoLogin()
        it.join()
    }
}
