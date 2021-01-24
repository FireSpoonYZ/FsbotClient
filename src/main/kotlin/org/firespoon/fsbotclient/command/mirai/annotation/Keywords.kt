package org.firespoon.fsbotclient.command.mirai.annotation

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Keywords(val keywords : Array<String>)
