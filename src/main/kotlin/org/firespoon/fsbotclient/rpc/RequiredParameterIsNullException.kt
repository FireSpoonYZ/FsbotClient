package org.firespoon.fsbotclient.rpc

class RequiredParameterIsNullException(param : RPCParam): Exception("Required parameter ${param.name} is null")
