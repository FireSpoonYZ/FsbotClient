package org.firespoon.fsbotclient.service

import org.firespoon.fsbotclient.model.FateResult
import org.firespoon.fsbotclient.model.FsResult
import org.firespoon.fsbotclient.fshttprequestclient.annotation.FsRequestClient
import org.firespoon.fsbotclient.fshttprequestclient.annotation.FsRequestMethod
import org.firespoon.fsbotclient.fshttprequestclient.annotation.FsRequestParam

@FsRequestClient("http://localhost/fate")
interface FateService {
    @FsRequestMethod("fate")
    fun fate( @FsRequestParam("time") time: Int?):
            FsResult<List<FateResult>>
}
