package org.firespoon.fsbotclient.rpc

import kotlin.reflect.KClass

class NotRPCClientException(clazz: KClass<*>): Exception("${clazz.simpleName} is not a rpc client")
