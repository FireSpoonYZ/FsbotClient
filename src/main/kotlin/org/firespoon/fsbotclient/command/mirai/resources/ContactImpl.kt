package org.firespoon.fsbotclient.command.mirai.resources

import org.firespoon.fsbotclient.command.resources.Contact

class ContactImpl(override val id: Long) : Contact {
    constructor(contact: net.mamoe.mirai.contact.Contact) : this(id = contact.id)
}
