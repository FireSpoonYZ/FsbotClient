package org.firespoon.fsbotclient.fshttprequestclient.annotation

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class FsRequestMethod(
    val path : String,
    val method : String = "GET"
)
