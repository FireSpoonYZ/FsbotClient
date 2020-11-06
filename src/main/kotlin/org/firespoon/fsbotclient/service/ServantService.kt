package org.firespoon.fsbotclient.service

import org.firespoon.fsbotclient.model.FsResult
import org.firespoon.fsbotclient.model.Servant
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestClient
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestPath
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestParam

@RequestClient("http://localhost/fate/servant")
interface ServantService {
    @RequestPath("random")
    fun random(
        @RequestParam("time") time: Int?,
        @RequestParam("class") clazz: String?,
        @RequestParam("user_id") userId: Long?,
        @RequestParam("command") command: String?
    ): FsResult<List<Servant>>

    @RequestPath(path = "delete", method = "DELETE")
    fun delete(
        @RequestParam("name") name: String,
        @RequestParam("clazz") clazz: String
    ): FsResult<Int>

    @RequestPath(path = "save", method = "POST")
    fun save(
        @RequestParam("name") name: String,
        @RequestParam("clazz") clazz: String
    ): FsResult<Int>

    @RequestPath("hassan")
    fun randomHassan(
        @RequestParam("time") time: Int?
    ): FsResult<List<Servant>>
}
