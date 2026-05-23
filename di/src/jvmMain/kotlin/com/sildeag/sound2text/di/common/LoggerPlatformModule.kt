package com.sildeag.sound2text.di.common

import com.sildeag.sound2text.core.logging.DesktopLogger
import com.sildeag.sound2text.core.common.logging.Logger
import org.koin.dsl.module

actual val loggerPlatformModule = module {
    single<Logger> { DesktopLogger() }
}