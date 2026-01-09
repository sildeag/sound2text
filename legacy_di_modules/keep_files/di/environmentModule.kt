package com.sildeag.sound2text.di

import com.sildeag.sound2text.config.AppEnvironment
import com.sildeag.sound2text.config.loadConfig
import org.koin.dsl.module
val environmentModule = module {
    single { loadConfig() } // provide Config
    single { AppEnvironment(get()) } // provide AppEnvironment
}
