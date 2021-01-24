package org.firespoon.fsbotclient.rpc

import kotlin.reflect.KFunction

class NotRPCRequestException(method: KFunction<*>): Exception(method.name + " is not a rpc request")
