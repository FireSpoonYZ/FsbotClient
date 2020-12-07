package org.firespoon.fsbotclient.function.env.arknights

import org.firespoon.fsbotclient.cli.EmptyCli
import org.firespoon.fsbotclient.cli.tag
import org.firespoon.fsbotclient.model.Tag

class ArkNightsCalcEnv : EmptyCli() {
    val tag1: Tag by tag()
    val tag2: Tag by tag()
    val tag3: Tag by tag()
    val tag4: Tag by tag()
    val tag5: Tag by tag()
}
