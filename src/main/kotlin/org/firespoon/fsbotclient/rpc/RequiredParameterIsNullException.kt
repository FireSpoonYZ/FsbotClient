package org.firespoon.fsbotclient.rpc

class RequiredParameterIsNullException(param : RPCParam): Exception(message = "Required parameter ${param.name} is null")
