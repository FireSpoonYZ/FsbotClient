package org.firespoon.fsbotclient.cli


class StringArgument : BaseArgument<String>() {
    override fun fromString(str: String): String? {
        return str
    }
}

fun BaseCli.string(): StringArgument {
    val arg = StringArgument()
    arguments.add(arg)
    return arg
}
