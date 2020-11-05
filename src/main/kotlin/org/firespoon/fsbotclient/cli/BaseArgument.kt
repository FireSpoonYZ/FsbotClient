package org.firespoon.fsbotclient.cli

import java.lang.IllegalArgumentException
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

abstract class BaseArgument<T> : ReadOnlyProperty<Any?, T> {
    var nullable = false
    var value: T? = null
    var default: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val res = if (nullable) {
            if (value != null) {
                value
            } else {
                default
            }
        } else {
            when {
                value != null -> {
                    value
                }
                default != null -> {
                    default
                }
                else -> {
                    throw IllegalArgumentException("不能为null")
                }
            }
        }
        return res as T
    }

    abstract fun fromString(str: String): T?
}

fun <TT, T: BaseArgument<TT>> T.nullable() : T {
    this.nullable = true
    return this
}

fun <TT, T: BaseArgument<TT>> T.default(value: TT) : T {
    this.default = value
    return this
}
