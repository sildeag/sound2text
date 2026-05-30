package com.sildeag.sound2text.di.logging

import com.sildeag.sound2text.core.logging.Logger
import com.sildeag.sound2text.core.logging.getPlatformLogger

import org.koin.dsl.module
val LoggingModule = module {
    single<Logger> { getPlatformLogger() }
}
