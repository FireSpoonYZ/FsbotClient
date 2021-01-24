package org.firespoon.fsbotclient.function.record

import org.firespoon.fsbotclient.service.RecordService
import org.firespoon.fsbotclient.utils.NetworkUtils

class RecordFunction {
    companion object {
        val recordService = NetworkUtils.buildService(RecordService::class)
    }
}
