package com.sildeag.sound2text.di

import org.koin.dsl.module

val loggingModule = module {
    single<Logger> { LoggerImpl() }
}
