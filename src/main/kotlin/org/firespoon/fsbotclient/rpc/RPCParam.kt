package org.firespoon.fsbotclient.rpc

@Target(AnnotationTarget.TYPE_PARAMETER, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class RPCParam(val name: String, val default: String = "")
