package com.sildeag.sound2text.featuresettings.di

import com.sildeag.sound2text.featuresettings.SettingsController
import com.sildeag.sound2text.featuresettings.SettingsRepository
import com.sildeag.sound2text.featuresettings.SettingsState
import org.koin.dsl.module
val settingsModule = module {
    // Shared settings state
    single { SettingsState() }
    // Shared settings repository (uses SQLite)
    single { SettingsRepository(get()) }
    // Shared settings controller
    single { SettingsController(get(), get()) }
}