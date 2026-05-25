package com.sildeag.sound2text.uilegacy.di

import com.sildeag.sound2text.uilegacy.settings.DesktopAppSettings
import org.koin.dsl.module
val desktopSettingsModule = module {
    single< DesktopAppSettings> { DesktopAppSettings() }
}
