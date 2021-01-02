package org.firespoon.fsbotclient.command.annotation

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Argument(val doc: String)
