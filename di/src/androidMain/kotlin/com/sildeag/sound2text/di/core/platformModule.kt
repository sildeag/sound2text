package com.sildeag.sound2text.di.core

import com.sildeag.sound2text.core.logging.AndroidLogger
import com.sildeag.sound2text.core.logging.Logger
import org.koin.dsl.module

actual val platformModule = module {
    single<Logger> { AndroidLogger() }
}