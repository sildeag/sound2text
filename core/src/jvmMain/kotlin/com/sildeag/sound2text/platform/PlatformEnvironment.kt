package com.sildeag.sound2text.platform

actual class PlatformEnvironment {
    actual val environment: String =
        System.getProperty("env") ?: "PROD"
    actual val isDebug: Boolean =
        environment.equals("DEV", ignoreCase = true)
}