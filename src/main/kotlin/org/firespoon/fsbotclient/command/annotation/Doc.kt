package org.firespoon.fsbotclient.command.annotation

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Doc(val doc : String = "")
