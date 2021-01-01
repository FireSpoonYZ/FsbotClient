package org.firespoon.fsbotclient.function.arknights

import org.firespoon.fsbotclient.service.ArkNightsService
import org.firespoon.fsbotclient.utils.NetworkUtils

class ArkNightsFunction {
    companion object {
        val service = NetworkUtils.buildService(ArkNightsService::class)
    }
}
