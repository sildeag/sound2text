package com.sildeag.sound2text.core.di

import com.sildeag.sound2text.core.config.AppEnvironment
import com.sildeag.sound2text.core.config.AppSettings
import org.koin.dsl.module

val appSettingsModule = module {
    single<AppSettings>
    { provideAppSettings(AppEnvironment.current) }
}