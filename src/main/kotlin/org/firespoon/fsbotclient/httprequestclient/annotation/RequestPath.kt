package org.firespoon.fsbotclient.httprequestclient.annotation

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class RequestPath(
    val path : String,
    val method : String = "GET"
)
