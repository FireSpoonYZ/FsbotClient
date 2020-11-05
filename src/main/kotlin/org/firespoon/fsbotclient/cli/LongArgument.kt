package org.firespoon.fsbotclient.cli

class LongArgument : BaseArgument<Long>() {
    override fun fromString(str: String): Long? {
        return str.toLongOrNull()
    }
}

fun BaseCli.long(): LongArgument {
    val arg = LongArgument()
    arguments.add(arg)
    return arg
}
