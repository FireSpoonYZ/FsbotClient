package org.firespoon.fsbotclient.function.jojo

import org.firespoon.fsbotclient.service.JojoService
import org.firespoon.fsbotclient.service.StandService
import org.firespoon.fsbotclient.utils.NetworkUtils

abstract class JojoFunction {
    companion object {
        val jojoService = NetworkUtils.buildService(JojoService::class)
        val standService = NetworkUtils.buildService(StandService::class)
    }
}
