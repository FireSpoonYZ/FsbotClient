package org.firespoon.fsbotclient.service

import org.firespoon.fsbotclient.model.FsResult
import org.firespoon.fsbotclient.model.JojoResult
import org.firespoon.fsbotclient.fshttprequestclient.annotation.FsRequestClient
import org.firespoon.fsbotclient.fshttprequestclient.annotation.FsRequestMethod
import org.firespoon.fsbotclient.fshttprequestclient.annotation.FsRequestParam
import org.firespoon.fsbotclient.model.Stand

@FsRequestClient("http://localhost/jojo")
interface JojoService {
    @FsRequestMethod("jojo")
    fun jojo(
        @FsRequestParam("time") time: Int?
    ): FsResult<List<JojoResult>>

    @FsRequestMethod("stand/random")
    fun random(
        @FsRequestParam("time") time: Int?
    ): FsResult<List<Stand>>
}
