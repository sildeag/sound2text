package com.sildeag.sound2text.core.logging

import com.sildeag.sound2text.core.common.logging.Logger

actual fun getPlatformLogger(): Logger =
    DesktopLogger()
