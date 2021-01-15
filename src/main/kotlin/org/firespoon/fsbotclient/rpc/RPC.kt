package org.firespoon.fsbotclient.rpc

import com.google.gson.reflect.TypeToken
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.internal.http.HttpMethod
import org.firespoon.fsbotclient.httprequestclient.annotation.RequestBody
import org.firespoon.fsbotclient.utils.JsonUtils
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.javaType

abstract class RPC {
    companion object {
        fun <T : Any> build(clazz: KClass<T>): T {
            val rpcClient = clazz.findAnnotation<RPCClient>() ?: throw NotRPCClientException(clazz)
            val uri = rpcClient.uri.trimEnd('/')


            class Handler : InvocationHandler {
                override fun invoke(proxy: Any, callingMethod: Method, args: Array<out Any?>): Any? {
                    var result: Any? = null

                    val requestBuilder = Request.Builder()
                        .addHeader("Content-Type", "application/json;charset=uft-8")

                    val urlBuilder = uri.toHttpUrlOrNull()?.newBuilder() ?: throw URLException(uri)

                    for (method in clazz.declaredMemberFunctions) {
                        if (callingMethod.name != method.name || !callingMethod.parameterTypes.contentEquals(method.javaClass.typeParameters)) {
                            continue
                        }

                        val rpcRequest = method.findAnnotation<RPCRequest>() ?: throw NotRPCRequestException(method)
                        val path = rpcRequest.path.trim('/')
                        urlBuilder.addPathSegments(path)
                        val parameters = method.parameters
                        var requestBody: okhttp3.RequestBody? = null
                        for (i in args.indices) {
                            val arg = args[i]
                            val parameter = parameters[i]
                            val param = parameter.findAnnotation<RPCParam>()
                            if (param != null) {
                                val argStr = arg?.toString()
                                    ?: if (param.default.isNotEmpty()) {
                                        param.default
                                    } else {
                                        throw RequiredParameterIsNullException(param)
                                    }
                                urlBuilder.addQueryParameter(param.name, argStr)
                            } else {
                                val body = parameter.findAnnotation<RequestBody>()
                                if (body != null) {
                                    val json = JsonUtils.toJson(parameter)
                                    val mediaType = "application/json".toMediaType()
                                    requestBody = json.toRequestBody(mediaType)
                                }
                            }
                        }

                        requestBuilder.url(urlBuilder.build())

                        if (HttpMethod.requiresRequestBody(rpcRequest.method.name) && requestBody == null) {
                            requestBody = "".toRequestBody()
                        }
                        requestBuilder.method(rpcRequest.method.name, requestBody)

                        val request = requestBuilder.build()
                        val client = OkHttpClient()
                        val call = client.newCall(request)
                        val response = call.execute()
                        if (response.code == 200) {
                            val json = response.body!!.string()
                            val returnType = method.returnType
                            val type = TypeToken.get(returnType.javaType).type
                            result = JsonUtils.fromJson(json, type)
                        } else {
                            throw RPCRequestFailedException(response.code, response.message)
                        }
                        response.body?.close()

                        break
                    }
                    return result
                }

            }

            val handler = Handler()
            val clazzArray = Array(1) {
                clazz.java
            }

            return Proxy.newProxyInstance(
                handler.javaClass.classLoader,
                clazzArray,
                handler
            ) as T
        }
    }
}
