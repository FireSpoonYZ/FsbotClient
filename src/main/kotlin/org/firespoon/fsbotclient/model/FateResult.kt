package org.firespoon.fsbotclient.model

class FateResult {
    val magicPowerLevel: String? = null
    val magicCircuitLevel: String? = null
    val attributes: Set<String>? = null
    val origin: String? = null
    val magicPower: Int? = null
    val birth: String? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("魔力:$magicPowerLevel ")
        sb.append("魔术回路:$magicCircuitLevel ")
        sb.append("属性:${attributes!!.joinToString(",")} ")
        sb.append("起源:$origin ")
        sb.append("魔力量:${magicPower} ")
        sb.append("出身:$birth")
        return sb.toString()
    }
}
