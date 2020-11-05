package org.firespoon.fsbotclient.function.env.coc

import org.firespoon.fsbotclient.cli.EmptyCli
import org.firespoon.fsbotclient.cli.default
import org.firespoon.fsbotclient.cli.string

class CocDiceEnv : EmptyCli() {
    val diceExp: String by string().default("1d100")
}
