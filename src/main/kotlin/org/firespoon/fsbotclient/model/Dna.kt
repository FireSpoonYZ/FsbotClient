package org.firespoon.fsbotclient.model

class Dna {
    val id: Int? = null
    val name: String? = null
    val rank: Int? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("☆".repeat(rank!!))
        sb.append(" ")
        sb.append(name!!)
        return sb.toString()
    }
}
