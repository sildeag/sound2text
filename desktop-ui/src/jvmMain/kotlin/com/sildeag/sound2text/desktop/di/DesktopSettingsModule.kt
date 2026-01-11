package com.sildeag.sound2text.desktop.di

import com.sildeag.sound2text.config.AppSettings
import com.sildeag.sound2text.desktop.settings.DesktopAppSettings
import org.koin.dsl.module
val desktopSettingsModule = module {
    single<AppSettings> { DesktopAppSettings() }
}