package org.firespoon.fsbotclient.function.env.coc

import org.firespoon.fsbotclient.cli.EmptyCli
import org.firespoon.fsbotclient.cli.long
import org.firespoon.fsbotclient.cli.string

class CocLoadCardEnv : EmptyCli() {
    val placeId: Long by long()
    val ownerId: Long by long()
    val name: String by string()
}