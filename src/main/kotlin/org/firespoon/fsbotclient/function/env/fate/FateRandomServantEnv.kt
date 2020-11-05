package org.firespoon.fsbotclient.function.env.fate

import org.firespoon.fsbotclient.cli.*

class FateRandomServantEnv : EmptyCli() {
    val userId: Long by long()
    val clazz: String? by clazz().nullable()
    val time: Int? by int().nullable()
    val command: String? by string().nullable()
}
