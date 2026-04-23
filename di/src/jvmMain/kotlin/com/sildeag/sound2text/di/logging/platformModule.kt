package com.sildeag.sound2text.di.logging

import com.sildeag.sound2text.core.logging.DesktopLogger
import com.sildeag.sound2text.core.logging.Logger
import org.koin.dsl.module

actual val platformModule = module {
    single<Logger> { DesktopLogger() }
}