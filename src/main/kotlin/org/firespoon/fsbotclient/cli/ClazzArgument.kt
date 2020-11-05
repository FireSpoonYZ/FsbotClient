package org.firespoon.fsbotclient.cli

class ClazzArgument : BaseArgument<String>() {
    override fun fromString(str: String): String? {
        return str.toClazzOrNull()
    }

    companion object {
        private val clazzList = listOf(
            "Saber",
            "Archer",
            "Lancer",
            "Rider",
            "Caster",
            "Assassin",
            "Berserker",
            "Shielder",
            "Ruler",
            "Avenger",
            "Alterego",
            "MoonCancer",
            "Foreigner"
        )

        fun toSingleClazzOrNull(clazz: String): String? {
            val lClazz = clazz.toLowerCase()
            for (res in clazzList) {
                if (res.toLowerCase() == lClazz) {
                    return res
                }
            }
            return null
        }

        fun String.toClazzOrNull(): String? {
            val clazzList = this.split(Regex("\\|"))
            var res = this
            for (clazz in clazzList) {
                val standard = toSingleClazzOrNull(clazz)
                if (standard == null) {
                    return null
                } else {
                    res = this.replace(clazz, standard)
                }
            }
            return res
        }
    }
}

fun BaseCli.clazz(): ClazzArgument {
    val arg = ClazzArgument()
    arguments.add(arg)
    return arg
}
