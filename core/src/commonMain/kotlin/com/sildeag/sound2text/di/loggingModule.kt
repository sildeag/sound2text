package com.sildeag.sound2text.di

import com.sildeag.sound2text.logging.LoggerImpl
import com.sildeag.sound2text.logging.Logger
import org.koin.dsl.module

val loggingModule = module {
    single<Logger> { LoggerImpl() }
}