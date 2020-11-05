package org.firespoon.fsbotclient.cli


class IntArgument : BaseArgument<Int>() {
    override fun fromString(str: String): Int? {
        return str.toIntOrNull()
    }
}

fun BaseCli.int(): IntArgument {
    val arg = IntArgument()
    arguments.add(arg)
    return arg
}
