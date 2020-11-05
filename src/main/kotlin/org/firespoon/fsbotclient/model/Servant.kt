package org.firespoon.fsbotclient.model

class Servant {
    val name: String? = null
    val clazz: String? = null

    override fun toString(): String {
        return "$clazz $name"
    }
}
