package com.sildeag.sound2text.di.settings

import org.koin.dsl.module

val settingsModule = module {
    single<Settings> { Settings() }
}