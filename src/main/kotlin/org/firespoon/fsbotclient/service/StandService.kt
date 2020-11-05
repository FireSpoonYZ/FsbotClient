package org.firespoon.fsbotclient.service

import org.firespoon.fsbotclient.model.FsResult
import org.firespoon.fsbotclient.model.Stand
import org.firespoon.fsbotclient.fshttprequestclient.annotation.FsRequestClient
import org.firespoon.fsbotclient.fshttprequestclient.annotation.FsRequestMethod
import org.firespoon.fsbotclient.fshttprequestclient.annotation.FsRequestParam

@FsRequestClient("http://localhost/jojo/stand/")
interface StandService {
    @FsRequestMethod("random")
    fun random(
        @FsRequestParam("time") time: Int?
    ): FsResult<List<Stand>>
}
