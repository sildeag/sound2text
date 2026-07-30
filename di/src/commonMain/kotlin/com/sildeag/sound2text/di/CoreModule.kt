package com.sildeag.sound2text.di

import com.sildeag.sound2text.core.config.AppSettings
import com.sildeag.sound2text.core.config.FeatureFlags

import org.koin.dsl.module

val coreModule = module {
    single { AppSettings(get()) }
    single { FeatureFlags(get()) }
}


