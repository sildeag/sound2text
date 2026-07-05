package com.sildeag.sound2text.di

import com.sildeag.sound2text.storage.PreferencesStore
import com.sildeag.sound2text.storage.StorageService
import com.sildeag.sound2text.storage.FileStorageService
import com.sildeag.sound2text.storage.desktop.DesktopPreferencesStore
import com.sildeag.sound2text.storage.desktop.DesktopStorageService
import com.sildeag.sound2text.storage.desktop.DesktopFileStorageService
import org.koin.dsl.module
import java.io.File
val desktopStorageModule = module {
    single<PreferencesStore> {
        DesktopPreferencesStore(File("data/${StoragePaths.PREFS_FILE}"))
    }
    single<StorageService> {
        DesktopStorageService(File("data"))
    }
    single<FileStorageService> {
        DesktopFileStorageService(File("data"))
    }
}