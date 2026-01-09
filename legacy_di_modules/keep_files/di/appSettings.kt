package com.sildeag.sound2text.di

import com.sildeag.sound2text.config.AppEnvironment
import com.sildeag.sound2text.config.AppSettings
import org.koin.dsl.module

val appSettingsModule = module {
    single<AppSettings>
    { provideAppSettings(AppEnvironment.current) }
}