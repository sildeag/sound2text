package com.sildeag.sound2text.uidesktop.di

import com.sildeag.sound2text.pdfdesktop.AppSettings
import com.sildeag.sound2text.uidesktop.settings.DesktopAppSettings
import org.koin.dsl.module
val desktopSettingsModule = module {
    single<AppSettings> { DesktopAppSettings() }
}
