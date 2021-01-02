package org.firespoon.fsbotclient.cli

class LongArgument(name: String) : BaseArgument<Long>(name, "数字") {
    override fun fromString(str: String): Long? {
        return str.toLongOrNull()
    }
}

fun BaseCli.long(name: String = ""): LongArgument {
    val arg = LongArgument(name)
    arguments.add(arg)
    return arg
}
