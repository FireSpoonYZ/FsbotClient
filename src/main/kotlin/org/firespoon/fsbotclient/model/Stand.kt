package org.firespoon.fsbotclient.model

class Stand {
    val englishName: String? = null
    val name: String? = null
    val owner: String? = null
    val ability: String? = null
    val power: String? = null
    val speed: String? = null
    val range: String? = null
    val lasting: String? = null
    val precision: String? = null
    val growth: String? = null

    override fun toString(): String {
        val sb = StringBuilder()
        val name = when {
            englishName == null -> name
            name == englishName -> name
            else -> "$name（$englishName）"
        }
        sb.append("替身名：$name\n")
        sb.append("力量：$power\n")
        sb.append("速度：$speed\n")
        sb.append("射程：$range\n")
        sb.append("持久：$lasting\n")
        sb.append("精密：$precision\n")
        sb.append("成长：$growth\n")
        sb.append("能力：$ability")
        return sb.toString()
    }
}