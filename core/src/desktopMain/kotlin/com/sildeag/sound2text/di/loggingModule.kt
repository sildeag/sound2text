package com.sildeag.sound2text.core.di

import com.sildeag.sound2text.core.logging.LoggerImpl
import com.sildeag.sound2text.core.logging.Logger
import org.koin.dsl.module

val loggingModule = module {
    single<Logger> { LoggerImpl() }
}