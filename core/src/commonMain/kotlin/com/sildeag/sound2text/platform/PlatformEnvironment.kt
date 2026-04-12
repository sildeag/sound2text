package com.sildeag.sound2text.core.platform

expect class PlatformEnvironment {
    val environment: String
    val isDebug: Boolean
}

