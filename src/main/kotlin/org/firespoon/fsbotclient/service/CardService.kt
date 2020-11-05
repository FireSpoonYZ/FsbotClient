package org.firespoon.fsbotclient.service

import org.firespoon.fsbotclient.model.Card
import org.firespoon.fsbotclient.model.FsResult
import org.firespoon.fsbotclient.fshttprequestclient.annotation.FsRequestClient
import org.firespoon.fsbotclient.fshttprequestclient.annotation.FsRequestMethod
import org.firespoon.fsbotclient.fshttprequestclient.annotation.FsRequestParam

@FsRequestClient("http://localhost/card")
interface CardService {
    @FsRequestMethod(path = "delete", method = "DELETE")
    fun delete(
        @FsRequestParam("owner_id") ownerId: Long,
        @FsRequestParam("name") name: String
    ) : FsResult<Int>

    @FsRequestMethod("get_all")
    fun getAll(
        @FsRequestParam("owner_id") ownerId: Long
    ): FsResult<List<Card>>
}
