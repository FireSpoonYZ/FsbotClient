package org.firespoon.fsbotclient.function.env.coc

import org.firespoon.fsbotclient.cli.EmptyCli
import org.firespoon.fsbotclient.cli.int
import org.firespoon.fsbotclient.cli.nullable


class CocCocEnv : EmptyCli() {
    val time: Int? by int().nullable()
}
