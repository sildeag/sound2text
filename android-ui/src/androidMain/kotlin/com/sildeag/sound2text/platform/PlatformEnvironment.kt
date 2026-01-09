package com.sildeag.sound2text.platform

actual class PlatformEnvironment {
    actual val environment: String =
        if (BuildConfig.DEBUG) "DEV" else "PROD"
    actual val isDebug: Boolean =
        BuildConfig.DEBUG
}