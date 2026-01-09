package com.sildeag.sound2text.di

import com.sildeag.sound2text.platform.SettingsLoader
import org.koin.dsl.module

val jvmConfigModule = module {
    single { SettingsLoader.load() }
}