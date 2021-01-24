package org.firespoon.fsbotclient.service

import org.firespoon.fsbotclient.httprequestclient.annotation.RequestClient
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestParam
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestPath
import org.firespoon.fsbotclient.model.FsResult
import org.firespoon.fsbotclient.model.JojoResult
import org.firespoon.fsbotclient.model.Record
import org.firespoon.fsbotclient.model.Stand

@RequestClient("http://localhost/record")
interface RecordService {
    @RequestPath("list")
    fun list(
        @RequestParam("owner_id") ownerId: Long
    ): FsResult<List<Record>>

    @RequestPath("save", method = "POST")
    fun save(
        @RequestParam("owner_id") ownerId: Long,
        @RequestParam("key") key: String,
        @RequestParam("value") value: String
    ): FsResult<String>

    @RequestPath("load")
    fun load(
        @RequestParam("owner_id") ownerId: Long,
        @RequestParam("key") key: String
    ): FsResult<String>

    @RequestPath("delete", method = "DELETE")
    fun delete(
        @RequestParam("owner_id") ownerId: Long,
        @RequestParam("key") key: String
    ): FsResult<String>
}
