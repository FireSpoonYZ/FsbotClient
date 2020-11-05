package org.firespoon.fsbotclient.utils

import org.firespoon.fsbotclient.fshttprequestclient.FsHttpRequestClient
import kotlin.reflect.KClass

abstract class NetworkUtils {
    companion object {
        fun <T : Any> buildService(clazz: KClass<T>) : T {
            val client = FsHttpRequestClient(clazz)
            return client.build()
        }
    }
}
