package org.firespoon.fsbotclient.function.env.fate

import org.firespoon.fsbotclient.cli.EmptyCli
import org.firespoon.fsbotclient.cli.int
import org.firespoon.fsbotclient.cli.nullable

class FateRandomHassanEnv : EmptyCli() {
    val time : Int? by int().nullable()
}
