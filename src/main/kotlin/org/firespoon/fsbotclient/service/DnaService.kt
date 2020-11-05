package org.firespoon.fsbotclient.service

import org.firespoon.fsbotclient.model.Dna
import org.firespoon.fsbotclient.model.FsResult
import org.firespoon.fsbotclient.fshttprequestclient.annotation.FsRequestClient
import org.firespoon.fsbotclient.fshttprequestclient.annotation.FsRequestMethod

@FsRequestClient("http://localhost/dna")
interface DnaService {
    @FsRequestMethod("random")
    fun random() : FsResult<List<Dna>>
}
