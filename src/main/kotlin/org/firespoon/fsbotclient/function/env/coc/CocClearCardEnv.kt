package org.firespoon.fsbotclient.function.env.coc

import org.firespoon.fsbotclient.cli.EmptyCli
import org.firespoon.fsbotclient.cli.long

class CocClearCardEnv : EmptyCli() {
    val placeId: Long by long()
    val ownerId: Long by long()
}