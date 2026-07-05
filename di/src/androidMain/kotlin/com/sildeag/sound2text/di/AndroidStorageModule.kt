package com.sildeag.sound2text.di

import com.sildeag.sound2text.storage.PreferencesStore
import com.sildeag.sound2text.storage.StorageService
import com.sildeag.sound2text.storage.FileStorageService
import com.sildeag.sound2text.storage.android.AndroidPreferencesStore
import com.sildeag.sound2text.storage.android.AndroidStorageService
import com.sildeag.sound2text.storage.android.AndroidFileStorageService
import org.koin.dsl.module
val androidStorageModule = module {
    single<PreferencesStore> {
        AndroidPreferencesStore(get()) // requires Android Context
    }
    single<StorageService> {
        AndroidStorageService(get()) // Context injected
    }
    single<FileStorageService> {
        AndroidFileStorageService(get()) // Context injected
    }
}