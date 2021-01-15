package org.firespoon.fsbotclient.rpc

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class RPCClient(val uri: String)
