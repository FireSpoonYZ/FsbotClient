package org.firespoon.fsbotclient.utils

import org.firespoon.fsbotclient.httprequestclient.HttpRequestClient
import kotlin.reflect.KClass

abstract class NetworkUtils {
    companion object {
        fun <T : Any> buildService(clazz: KClass<T>) : T {
            val client = HttpRequestClient(clazz)
            return client.build()
        }
    }
}
