package org.firespoon.fsbotclient.service

import org.firespoon.fsbotclient.model.FsResult
import org.firespoon.fsbotclient.model.JojoResult
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestClient
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestPath
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestParam
import org.firespoon.fsbotclient.model.Stand

@RequestClient("http://localhost/jojo")
interface JojoService {
    @RequestPath("jojo")
    fun jojo(
        @RequestParam("time") time: Int?
    ): FsResult<List<JojoResult>>

    @RequestPath("stand/random")
    fun random(
        @RequestParam("time") time: Int?
    ): FsResult<List<Stand>>
}
