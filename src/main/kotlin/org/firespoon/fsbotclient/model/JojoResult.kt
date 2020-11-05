package org.firespoon.fsbotclient.model

class JojoResult {
    val ability: String? = null
    val power: String? = null
    val speed: String? = null
    val range: String? = null
    val lasting: String? = null
    val precision: String? = null
    val growth: String? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("力量：$power ")
        sb.append("速度：$speed ")
        sb.append("射程：$range ")
        sb.append("持久：$lasting ")
        sb.append("精密：$precision ")
        sb.append("成长：$growth ")
        sb.append("能力：$ability")
        return sb.toString()
    }
}
