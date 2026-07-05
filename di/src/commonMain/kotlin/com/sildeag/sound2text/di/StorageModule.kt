package com.sildeag.sound2text.di

import com.sildeag.sound2text.settings.SettingsRepository
import com.sildeag.sound2text.storage.StorageService
import com.sildeag.sound2text.storage.FileStorageService
import com.sildeag.sound2text.storage.PreferencesStore
import org.koin.dsl.module
val storageModule = module {
    // PreferencesStore is platform-specific and provided in androidMain/desktopMain
    // StorageService is platform-specific; binding provided in platform modules
    // FileStorageService is platform-specific; binding provided in platform modules
}
