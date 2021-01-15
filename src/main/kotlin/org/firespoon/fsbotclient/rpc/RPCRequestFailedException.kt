package org.firespoon.fsbotclient.rpc

class RPCRequestFailedException(code: Int, message: String) : Exception(message = "请求失败，错误代码:$code 错误信息:$message")
