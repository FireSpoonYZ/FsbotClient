package org.firespoon.fsbotclient.service

import org.firespoon.fsbotclient.model.FsResult

import org.firespoon.fsbotclient.httprequestclient.annotation.RequestPath
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestClient
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestParam
import org.firespoon.fsbotclient.model.DiceResult

@RequestClient("http://localhost:80/dice")
interface DiceService {
    @RequestPath("dice")
    fun dice(@RequestParam("dice_exp") diceExp: String): FsResult<DiceResult>
}
