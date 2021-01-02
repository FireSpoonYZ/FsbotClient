package org.firespoon.fsbotclient.function

import net.mamoe.mirai.message.MessageEvent
import org.firespoon.fsbotclient.cli.BaseArgument
import org.firespoon.fsbotclient.cli.nullable
import org.firespoon.fsbotclient.cli.string
import org.firespoon.fsbotclient.command.FsCommand
import org.firespoon.fsbotclient.command.annotation.Doc
import org.firespoon.fsbotclient.command.annotation.Keywords
import org.firespoon.fsbotclient.model.FsResult
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.findAnnotation


@Keywords([".help"])
@Doc("Command Helper")
class HelpCommand : FsCommand<List<CommandClass>, MessageEvent>() {
    companion object {
        private val commandMap = mutableMapOf<String, CommandClass>()
        private val commandList = mutableListOf<CommandClass>()

        fun registerCommand(command: CommandClass) {
            val keywords = command.findAnnotation<Keywords>()!!.keywords
            for (keyword in keywords) {
                if (commandMap[keyword] != null) {
                    throw Exception("关键字重复！")
                }
            }
            for (keyword in keywords) {
                commandMap[keyword] = command
            }
            commandList.add(command)
        }
    }

    val keyword: String? by string("").nullable()

    override fun result(): FsResult<List<CommandClass>> {
        val res = FsResult<List<CommandClass>>()
        res.code = 200
        res.data = if (keyword != null) {
            var command = commandMap[keyword]
            command = command ?: commandMap[".$keyword"]
            if (command != null) {
                listOf(command)
            } else {
                emptyList()
            }
        } else {
            commandList
        }
        return res
    }

    override fun message(result: List<CommandClass>): String {
        return when (result.size) {
            0 -> "未找到命令$keyword"
            1 -> {
                val command = result[0]

                val sb = StringBuilder()


                val doc = command.findAnnotation<Doc>()!!.doc
                sb.append("\n功能:$doc\n")

                val keywords = command.findAnnotation<Keywords>()!!.keywords
                sb.append(keywords.joinToString(prefix = "关键字:[", separator = ", ", postfix = "]"))

                val obj = command.createInstance()
                val args = obj.arguments.filter {
                    it.name != ""
                }
                if (args.isNotEmpty()) {
                    sb.append(args.joinToString(prefix = "\n参数:\n", separator = "\n") {
                        val tsb = StringBuilder("  -${it.name}[类型:${it.type}]")
                        if (it.nullable) {
                            tsb.append("[可选]")
                        }
                        if (it.default != null) {
                            tsb.append("[默认值:${it.default}]")
                        }
                        tsb.toString()
                    })
                }

                sb.toString()
            }
            else -> {
                result.joinToString(prefix = "\n", separator = "\n") {
                    val keywords = it.findAnnotation<Keywords>()!!.keywords
                    val doc = it.findAnnotation<Doc>()!!.doc
                    keywords.joinToString(prefix = "$doc:[", separator = " ", postfix = "]")
                }
            }
        }
    }
}

typealias CommandClass = KClass<out FsCommand<*, *>>;
