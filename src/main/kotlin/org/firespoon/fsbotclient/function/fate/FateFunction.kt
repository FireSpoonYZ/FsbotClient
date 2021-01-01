package org.firespoon.fsbotclient.function.fate

import org.firespoon.fsbotclient.service.FateService
import org.firespoon.fsbotclient.service.ServantService
import org.firespoon.fsbotclient.utils.NetworkUtils

abstract class FateFunction {
    companion object {
        val fateService = NetworkUtils.buildService(FateService::class)
        val servantService = NetworkUtils.buildService(ServantService::class)
    }
}
