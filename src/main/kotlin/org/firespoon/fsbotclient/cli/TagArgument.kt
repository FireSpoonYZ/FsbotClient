package org.firespoon.fsbotclient.cli

import org.firespoon.fsbotclient.model.Tag

class TagArgument : BaseArgument<Tag>() {
    override fun fromString(str: String): Tag? {
        val name = when {
            listOf("资深").contains(str) -> "资深干员"
            listOf("高资").contains(str) -> "高级资深干员"
            listOf("近战").contains(str) -> "近战位"
            listOf("远程").contains(str) -> "远程位"
            listOf("先锋").contains(str) -> "先锋干员"
            listOf("狙击").contains(str) -> "狙击干员"
            listOf("医疗").contains(str) -> "医疗干员"
            listOf("术士").contains(str) -> "术士干员"
            listOf("近卫").contains(str) -> "近卫干员"
            listOf("重装").contains(str) -> "重装干员"
            listOf("辅助").contains(str) -> "辅助干员"
            listOf("特种").contains(str) -> "特种干员"
            listOf("快活").contains(str) -> "快速复活"
            listOf("回费").contains(str) -> "费用回复"
            else -> str
        }

        for (tag : Tag in Tag.values()) {
            if (tag.str == name) {
                return tag
            }
        }
        return null
    }
}

fun BaseCli.tag(): TagArgument {
    val arg = TagArgument()
    arguments.add(arg)
    return arg
}
