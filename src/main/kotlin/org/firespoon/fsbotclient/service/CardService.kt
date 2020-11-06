package org.firespoon.fsbotclient.service

import org.firespoon.fsbotclient.model.Card
import org.firespoon.fsbotclient.model.FsResult
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestClient
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestPath
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestParam

@RequestClient("http://localhost/card")
interface CardService {
    @RequestPath(path = "delete", method = "DELETE")
    fun delete(
        @RequestParam("owner_id") ownerId: Long,
        @RequestParam("name") name: String
    ) : FsResult<Int>

    @RequestPath("get_all")
    fun getAll(
        @RequestParam("owner_id") ownerId: Long
    ): FsResult<List<Card>>
}
