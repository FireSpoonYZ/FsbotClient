package org.firespoon.fsbotclient.function.env.coc

import org.firespoon.fsbotclient.cli.*

class CocCheckEnv : EmptyCli() {
    val placeId: Long by long()
    val ownerId: Long by long()
    val name: String by string()
    val value: Int? by int().nullable()
}
