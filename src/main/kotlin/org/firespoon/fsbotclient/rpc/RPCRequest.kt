package org.firespoon.fsbotclient.rpc

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class RPCRequest(
    val path : String,
    val method : RPCMethod = RPCMethod.GET
)
