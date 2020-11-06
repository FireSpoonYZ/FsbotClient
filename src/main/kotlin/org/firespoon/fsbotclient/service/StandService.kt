package org.firespoon.fsbotclient.service

import org.firespoon.fsbotclient.model.FsResult
import org.firespoon.fsbotclient.model.Stand
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestClient
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestPath
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestParam

@RequestClient("http://localhost/jojo/stand/")
interface StandService {
    @RequestPath("random")
    fun random(
        @RequestParam("time") time: Int?
    ): FsResult<List<Stand>>
}
