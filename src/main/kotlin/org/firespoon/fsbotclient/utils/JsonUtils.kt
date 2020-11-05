package org.firespoon.fsbotclient.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

abstract class JsonUtils {
    companion object {
        private val gson = Gson()
        fun <T> fromJson(json: String, type: Type): T {
            return gson.fromJson(json, type)
        }

        fun <T> fromJson(json: String): T {
            val mType = object : TypeToken<T>() {}.type
            return gson.fromJson(json, mType)
        }

        fun <T> toJson(obj: T): String {
            return gson.toJson(obj)
        }
    }
}