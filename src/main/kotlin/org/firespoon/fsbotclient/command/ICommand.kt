package org.firespoon.fsbotclient.command

import net.mamoe.mirai.event.events.MessageEvent

interface ICommand {
    fun getMessage(event: MessageEvent): String
}
