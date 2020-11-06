package org.firespoon.fsbotclient.service

import org.firespoon.fsbotclient.model.Dna
import org.firespoon.fsbotclient.model.FsResult
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestClient
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestPath

@RequestClient("http://localhost/dna")
interface DnaService {
    @RequestPath("random")
    fun random() : FsResult<List<Dna>>
}
