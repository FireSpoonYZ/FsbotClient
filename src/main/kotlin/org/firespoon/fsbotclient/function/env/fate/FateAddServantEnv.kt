package org.firespoon.fsbotclient.function.env.fate

import org.firespoon.fsbotclient.cli.*

class FateAddServantEnv : EmptyCli() {
    val clazz: String by clazz()
    val name: String by string()
}