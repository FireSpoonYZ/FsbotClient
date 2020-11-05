package org.firespoon.fsbotclient

import net.mamoe.mirai.Bot
import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.join
import org.firespoon.fsbotclient.command.register
import org.firespoon.fsbotclient.function.CocFunction
import org.firespoon.fsbotclient.function.DnaFunction
import org.firespoon.fsbotclient.function.FateFunction
import org.firespoon.fsbotclient.function.JojoFunction

suspend fun main() {
    val qqId = 744821060L
    val password = "1!2@3#skyYZ"

    val bot = Bot(qqId, password) {
        fileBasedDeviceInfo()
    }

    bot.register(CocFunction.diceCommand)
    bot.register(CocFunction.setCommand)
    bot.register(CocFunction.checkCommand)
    bot.register(CocFunction.saveCardCommand)
    bot.register(CocFunction.loadCardCommand)
    bot.register(CocFunction.clearCardCommand)
    bot.register(CocFunction.deleteCardCommand)
    bot.register(CocFunction.allCardCommand)

    bot.register(DnaFunction.randomDnaCommand)

    bot.register(FateFunction.fateCommand)
    bot.register(FateFunction.randomServantCommand)
    bot.register(FateFunction.addServantCommand)
    bot.register(FateFunction.deleteServantCommand)
    bot.register(FateFunction.randomHassanCommand)

    bot.register(JojoFunction.jojoCommand)
    bot.register(JojoFunction.randomStandCommand)

    bot.alsoLogin()
    bot.join()
}
