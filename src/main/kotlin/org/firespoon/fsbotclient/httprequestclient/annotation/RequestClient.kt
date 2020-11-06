package org.firespoon.fsbotclient.httprequestclient.annotation

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class RequestClient(val uri: String)