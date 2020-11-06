package org.firespoon.fsbotclient.service

import org.firespoon.fsbotclient.model.FateResult
import org.firespoon.fsbotclient.model.FsResult
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestClient
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestPath
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestParam

@RequestClient("http://localhost/fate")
interface FateService {
    @RequestPath("fate")
    fun fate( @RequestParam("time") time: Int?):
            FsResult<List<FateResult>>
}
