package com.sildeag.sound2text.core.logging

actual fun getPlatformLogger(): Logger =
    DesktopLogger()