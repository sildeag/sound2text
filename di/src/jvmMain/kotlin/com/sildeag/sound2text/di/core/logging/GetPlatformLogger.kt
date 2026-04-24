package com.sildeag.sound2text.di.core.logging

import com.sildeag.sound2text.core.logging.Logger

fun getPlatformLogger(): Logger = DesktopLogger()