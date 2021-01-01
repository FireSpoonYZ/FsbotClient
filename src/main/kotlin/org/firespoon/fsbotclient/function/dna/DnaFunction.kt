package org.firespoon.fsbotclient.function.dna

import org.firespoon.fsbotclient.service.DnaService
import org.firespoon.fsbotclient.utils.NetworkUtils

abstract class DnaFunction {
    companion object {
        val dnaService = NetworkUtils.buildService(DnaService::class)
    }
}
