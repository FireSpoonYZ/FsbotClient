package org.firespoon.fsbotclient.model

import org.firespoon.fsbotclient.utils.JsonUtils

class FsResult<T> {
    var code: Int? = null
    var message: String? = null
    var data: T? = null

    override fun toString(): String {
        return JsonUtils.toJson(this)
    }
}
