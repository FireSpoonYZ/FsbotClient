package org.firespoon.fsbotclient.service

import org.firespoon.fsbotclient.model.FsResult
import org.firespoon.fsbotclient.model.Servant
import org.firespoon.fsbotclient.fshttprequestclient.annotation.FsRequestClient
import org.firespoon.fsbotclient.fshttprequestclient.annotation.FsRequestMethod
import org.firespoon.fsbotclient.fshttprequestclient.annotation.FsRequestParam

@FsRequestClient("http://localhost/fate/servant")
interface ServantService {
    @FsRequestMethod("random")
    fun random(
        @FsRequestParam("time") time: Int?,
        @FsRequestParam("class") clazz: String?,
        @FsRequestParam("user_id") userId: Long?,
        @FsRequestParam("command") command: String?
    ): FsResult<List<Servant>>

    @FsRequestMethod(path = "delete", method = "DELETE")
    fun delete(
        @FsRequestParam("name") name: String,
        @FsRequestParam("clazz") clazz: String
    ): FsResult<Int>

    @FsRequestMethod(path = "save", method = "POST")
    fun save(
        @FsRequestParam("name") name: String,
        @FsRequestParam("clazz") clazz: String
    ): FsResult<Int>

    @FsRequestMethod("hassan")
    fun randomHassan(
        @FsRequestParam("time") time: Int?
    ): FsResult<List<Servant>>
}
