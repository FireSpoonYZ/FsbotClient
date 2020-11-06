package org.firespoon.fsbotclient.service

import org.firespoon.fsbotclient.model.CheckResult
import org.firespoon.fsbotclient.model.FsResult
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestClient
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestPath
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestParam

@RequestClient("http://localhost/coc")
interface CocService {
    @RequestPath("init")
    fun init(
        @RequestParam("place_id") placeId: Long,
        @RequestParam("owner_id") ownerId: Long
    ): FsResult<Int>

    @RequestPath("load_card")
    fun loadCard(
        @RequestParam("place_id") placeId: Long,
        @RequestParam("owner_id") ownerId: Long,
        @RequestParam("name") name: String
    ): FsResult<Int>

    @RequestPath("save_card")
    fun saveCard(
        @RequestParam("place_id") placeId: Long,
        @RequestParam("owner_id") ownerId: Long,
        @RequestParam("name") name: String
    ): FsResult<Int>

    @RequestPath("get_property")
    fun getProperty(
        @RequestParam("place_id") placeId: Long,
        @RequestParam("owner_id") ownerId: Long,
        @RequestParam("name") name: String
    ): FsResult<Int>

    @RequestPath("set_property")
    fun setProperty(
        @RequestParam("place_id") placeId: Long,
        @RequestParam("owner_id") ownerId: Long,
        @RequestParam("name") name: String,
        @RequestParam("value") value: Int?
    ): FsResult<Int>

    @RequestPath("check")
    fun check(
        @RequestParam("place_id") placeId: Long,
        @RequestParam("owner_id") ownerId: Long,
        @RequestParam("name") name: String,
        @RequestParam("value") value: Int?
    ): FsResult<CheckResult>
}
