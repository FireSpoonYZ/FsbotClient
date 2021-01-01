package org.firespoon.fsbotclient.command.annotation

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Keywords(val keywords : Array<String>)

