package org.firespoon.fsbotclient.service

import org.firespoon.fsbotclient.model.FsResult

import org.firespoon.fsbotclient.fshttprequestclient.annotation.FsRequestMethod
import org.firespoon.fsbotclient.fshttprequestclient.annotation.FsRequestClient
import org.firespoon.fsbotclient.fshttprequestclient.annotation.FsRequestParam
import org.firespoon.fsbotclient.model.DiceResult

@FsRequestClient("http://localhost:80/dice")
interface DiceService {
    @FsRequestMethod("dice")
    fun dice(@FsRequestParam("dice_exp") diceExp: String): FsResult<DiceResult>
}
