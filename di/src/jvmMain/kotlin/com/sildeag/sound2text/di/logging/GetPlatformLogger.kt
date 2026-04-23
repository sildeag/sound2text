package com.sildeag.sound2text.di.logging

import com.sildeag.sound2text.core.logging.Logger

fun getPlatformLogger(): Logger = DesktopLogger()