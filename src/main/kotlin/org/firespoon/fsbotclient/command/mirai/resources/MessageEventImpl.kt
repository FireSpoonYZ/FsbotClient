package org.firespoon.fsbotclient.command.mirai.resources

import net.mamoe.mirai.message.data.content
import org.firespoon.fsbotclient.command.resources.Contact
import org.firespoon.fsbotclient.command.resources.MessageEvent

class MessageEventImpl(
    override val message: String,
    override val subject: Contact,
    override val sender: Contact
) : MessageEvent {
    constructor(event: net.mamoe.mirai.event.events.MessageEvent) : this(
        message = event.message.content,
        subject = ContactImpl(event.subject),
        sender = ContactImpl(event.sender)
    )
}
