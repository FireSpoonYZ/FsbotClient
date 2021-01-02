package org.firespoon.fsbotclient.cli

class StringArgument(name: String) : BaseArgument<String>(name, "文字") {
    override fun fromString(str: String): String {
        return str
    }
}

fun BaseCli.string(name: String = ""): StringArgument {
    val arg = StringArgument(name)
    arguments.add(arg)
    return arg
}
