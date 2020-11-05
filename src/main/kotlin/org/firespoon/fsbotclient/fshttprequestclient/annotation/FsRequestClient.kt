package org.firespoon.fsbotclient.fshttprequestclient.annotation

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class FsRequestClient(val uri: String)