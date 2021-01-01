package org.firespoon.fsbotclient.command

import net.mamoe.mirai.message.MessageEvent

interface ICommand<M : MessageEvent> {
    fun getMessage(event: M): String
}
