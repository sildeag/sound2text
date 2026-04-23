package com.sildeag.sound2text.di.common

import com.sildeag.sound2text.core.logging.Logger
import org.koin.dsl.module
val LoggingModule = module {
    single<Logger> { getPlatformLogger() }
}
