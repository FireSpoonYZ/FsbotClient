package org.firespoon.fsbotclient.function.env.coc

import org.firespoon.fsbotclient.cli.EmptyCli
import org.firespoon.fsbotclient.cli.long

class CocAllCardEnv : EmptyCli() {
    val ownerId: Long by long()
}
