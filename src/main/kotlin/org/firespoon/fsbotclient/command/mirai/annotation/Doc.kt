package org.firespoon.fsbotclient.command.mirai.annotation

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Doc(val doc : String = "")
