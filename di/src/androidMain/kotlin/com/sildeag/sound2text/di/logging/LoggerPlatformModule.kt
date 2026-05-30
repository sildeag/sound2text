package com.sildeag.sound2text.di.common.logging

import com.sildeag.sound2text.core.logging.AndroidLogger
import com.sildeag.sound2text.core.logging.Logger
import org.koin.dsl.module

actual val loggerPlatformModule = module {
    single<Logger> { AndroidLogger() }
}
