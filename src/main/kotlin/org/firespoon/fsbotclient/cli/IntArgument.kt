package org.firespoon.fsbotclient.cli

class IntArgument(name: String) : BaseArgument<Int>(name, "数字") {
    override fun fromString(str: String): Int? {
        return str.toIntOrNull()
    }
}

fun BaseCli.int(name: String = ""): IntArgument {
    val arg = IntArgument(name)
    arguments.add(arg)
    return arg
}
