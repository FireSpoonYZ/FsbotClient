package org.firespoon.fsbotclient.function.env.jojo

import org.firespoon.fsbotclient.cli.EmptyCli
import org.firespoon.fsbotclient.cli.int
import org.firespoon.fsbotclient.cli.nullable

class JojoJojoEnv : EmptyCli() {
    val time : Int? by int().nullable()
}
