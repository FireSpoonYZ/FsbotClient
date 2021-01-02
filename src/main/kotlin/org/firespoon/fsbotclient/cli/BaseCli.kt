package org.firespoon.fsbotclient.cli

abstract class BaseCli {
    val arguments: MutableList<BaseArgument<*>> = mutableListOf()

    abstract fun run()

    fun parse(args: List<String>) {
        var index = 0
        for (_arg in arguments) {
            val arg = _arg as BaseArgument<Any?>
            if (index >= args.size) {
                break
            }
            val str = args[index]
            val value = arg.fromString(str)
            if (value != null) {
                arg.value = value
                index += 1
            } else {
                require(arg.nullable) {
                    "参数错误"
                }
            }
        }
        require(index >= args.size) {
            "参数过多"
        }
        for (arg in arguments) {
            require(arg.value != null || arg.nullable) {
                "参数错误"
            }
        }
    }

    fun call(args: List<String>) {
        parse(args)
        run()
    }
}
