package org.firespoon.fsbotclient.rpc

import kotlin.reflect.KFunction

class URLException(url: String): Exception(message = "$url is not a url")
