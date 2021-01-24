package org.firespoon.fsbotclient.command.resources

interface MessageEvent {
    val message: String
    val subject: Contact
    val sender: Contact
}
