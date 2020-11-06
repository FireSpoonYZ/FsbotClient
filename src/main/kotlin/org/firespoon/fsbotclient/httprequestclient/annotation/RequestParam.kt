package org.firespoon.fsbotclient.httprequestclient.annotation

@Target(AnnotationTarget.TYPE_PARAMETER, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class RequestParam(val value: String)
