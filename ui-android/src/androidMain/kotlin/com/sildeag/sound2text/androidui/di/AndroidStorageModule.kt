package com.sildeag.sound2text.androidui.di

import com.sildeag.sound2text.core.storage.FileStorageService
import com.sildeag.sound2text.android.storage.AndroidFileStorageService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
val androidStorageModule = module {
    single<FileStorageService> {
        AndroidFileStorageService(
            context = androidContext()
        )
    }
}
