package org.firespoon.fsbotclient.service

import org.firespoon.fsbotclient.model.CheckResult
import org.firespoon.fsbotclient.model.FsResult
import org.firespoon.fsbotclient.fshttprequestclient.annotation.FsRequestClient
import org.firespoon.fsbotclient.fshttprequestclient.annotation.FsRequestMethod
import org.firespoon.fsbotclient.fshttprequestclient.annotation.FsRequestParam

@FsRequestClient("http://localhost/coc")
interface CocService {
    @FsRequestMethod("init")
    fun init(
        @FsRequestParam("place_id") placeId: Long,
        @FsRequestParam("owner_id") ownerId: Long
    ): FsResult<Int>

    @FsRequestMethod("load_card")
    fun loadCard(
        @FsRequestParam("place_id") placeId: Long,
        @FsRequestParam("owner_id") ownerId: Long,
        @FsRequestParam("name") name: String
    ): FsResult<Int>

    @FsRequestMethod("save_card")
    fun saveCard(
        @FsRequestParam("place_id") placeId: Long,
        @FsRequestParam("owner_id") ownerId: Long,
        @FsRequestParam("name") name: String
    ): FsResult<Int>

    @FsRequestMethod("get_property")
    fun getProperty(
        @FsRequestParam("place_id") placeId: Long,
        @FsRequestParam("owner_id") ownerId: Long,
        @FsRequestParam("name") name: String
    ): FsResult<Int>

    @FsRequestMethod("set_property")
    fun setProperty(
        @FsRequestParam("place_id") placeId: Long,
        @FsRequestParam("owner_id") ownerId: Long,
        @FsRequestParam("name") name: String,
        @FsRequestParam("value") value: Int?
    ): FsResult<Int>

    @FsRequestMethod("check")
    fun check(
        @FsRequestParam("place_id") placeId: Long,
        @FsRequestParam("owner_id") ownerId: Long,
        @FsRequestParam("name") name: String,
        @FsRequestParam("value") value: Int?
    ): FsResult<CheckResult>
}
