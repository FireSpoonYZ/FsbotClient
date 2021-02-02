package org.firespoon.fsbotclient.cli

import java.lang.IllegalArgumentException
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

abstract class BaseArgument<T>(val name: String, val type: String) : ReadOnlyProperty<Any?, T> {
    var nullable = false
    var value: T? = null
        get() = when {
            field != null -> {
                field
            }
            default != null -> {
                default
            }
            nullable -> {
                null
            }
            else -> {
                throw IllegalArgumentException("不能为null")
            }
        }

    var default: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value as T
    }

    abstract fun fromString(str: String): T?
}

fun <TT, T : BaseArgument<TT>> T.nullable(): T {
    this.nullable = true
    return this
}

fun <TT, T : BaseArgument<TT>> T.default(value: TT): T {
    this.default = value
    return this
}
