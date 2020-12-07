package org.firespoon.fsbotclient.service

import org.firespoon.fsbotclient.httprequestclient.annotation.RequestClient
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestParam
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestPath
import org.firespoon.fsbotclient.model.Agent
import org.firespoon.fsbotclient.model.FsResult
import org.firespoon.fsbotclient.model.Tag

@RequestClient("http://localhost/arknights")
interface ArkNightsService {
    @RequestPath(path = "calc")
    fun calculate(
        @RequestParam("tag1") tag1: Tag,
        @RequestParam("tag2") tag2: Tag,
        @RequestParam("tag3") tag3: Tag,
        @RequestParam("tag4") tag4: Tag,
        @RequestParam("tag5") tag5: Tag
    ): FsResult<List<Pair<List<Tag>, List<Agent>>>>
}
