package org.firespoon.fsbotclient.httprequestclient

import com.google.gson.reflect.TypeToken
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.internal.http.HttpMethod
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestBody
import org.firespoon.fsbotclient.model.FsResult
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestPath
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestClient
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestParam
import org.firespoon.fsbotclient.utils.JsonUtils
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import kotlin.reflect.KClass

class HttpRequestClient<T : Any>(private val kClazz: KClass<T>) {
    fun <T> build(): T {
        val clazz = kClazz.java
        val fsRequestClient = clazz.getDeclaredAnnotation(RequestClient::class.java)
        require(fsRequestClient != null) {
            "目标不是FsRequestClient"
        }
        var uri = fsRequestClient.uri
        if (uri.endsWith("/")) {
            uri = uri.substring(0, uri.length)
        }

        class Handler : InvocationHandler {
            override fun invoke(proxy: Any, callingMethod: Method, args: Array<out Any?>?): Any? {
                var result: Any? = null

                val requestBuilder = Request.Builder()
                    .addHeader("Content-Type", "application/json;charset=uft-8")

                val urlBuilder = uri.toHttpUrlOrNull()!!.newBuilder()

                for (method in clazz.declaredMethods) {
                    if (callingMethod.name != method.name || !callingMethod.parameterTypes.contentEquals(method.parameterTypes)) {
                        continue
                    }
                    val fsRequest = method.getDeclaredAnnotation(RequestPath::class.java)
                    if (fsRequest != null) {
                        var path = fsRequest.path
                        if (path.startsWith("/")) {
                            path = path.substring(1)
                        }
                        urlBuilder.addPathSegments(path)
                        val parameters = method.parameters
                        var requestBody: okhttp3.RequestBody? = null
                        if (args != null) {
                            for (i in args.indices) {
                                val arg = args[i] ?: continue
                                val parameter = parameters[i]
                                val fsRequestParam = parameter.getAnnotation(RequestParam::class.java)
                                if (fsRequestParam != null) {
                                    urlBuilder.addQueryParameter(
                                        fsRequestParam.value, arg.toString()
                                    )
                                } else {
                                    val fsRequestBody = parameter.getAnnotation(RequestBody::class.java)
                                    if (fsRequestBody != null) {
                                        val json = JsonUtils.toJson(parameter)
                                        val mediaType = "application/json".toMediaType()
                                        requestBody = json.toRequestBody(mediaType)
                                    }
                                }
                            }
                        }

                        val url = urlBuilder.build()
                        println(url.toString())
                        requestBuilder.url(urlBuilder.build())

                        if (HttpMethod.requiresRequestBody(fsRequest.method) && requestBody == null) {
                            requestBody = "".toRequestBody()
                        }
                        requestBuilder.method(fsRequest.method, requestBody)

                        val request = requestBuilder.build()
                        val client = OkHttpClient()
                        val call = client.newCall(request)
                        val response = call.execute()
                        if (response.code == 200) {
                            val json = response.body!!.string()
                            val returnType = method.genericReturnType
                            val type = TypeToken.get(returnType).type
                            result = JsonUtils.fromJson(json, type)
                        } else {
                            result = FsResult<Nothing>()
                            result.code = 404
                            result.message = response.message
                        }
                        response.body?.close()
                    }
                }
                return result
            }

        }

        val handler = Handler()
        val clazzArray = Array(1) {
            clazz
        }

        return Proxy.newProxyInstance(
            handler.javaClass.classLoader,
            clazzArray,
            handler
        ) as T
    }
}
