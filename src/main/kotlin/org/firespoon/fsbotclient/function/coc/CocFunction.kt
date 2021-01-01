package org.firespoon.fsbotclient.function.coc

import org.firespoon.fsbotclient.service.CardService
import org.firespoon.fsbotclient.service.CocService
import org.firespoon.fsbotclient.service.DiceService
import org.firespoon.fsbotclient.utils.NetworkUtils

abstract class CocFunction {
    companion object {
        val diceService = NetworkUtils.buildService(DiceService::class)
        val cocService = NetworkUtils.buildService(CocService::class)
        val cardService = NetworkUtils.buildService(CardService::class)
    }
}
