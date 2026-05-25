package com.sildeag.sound2text.androidui.di

import com.sildeag.sound2text.core.storage.FileStorageService
import com.sildeag.sound2text.android.storage.AndroidFileStorageService
import org.koin.android.ext.koin.android// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
import org.koin.dsl.module
val androidStorageModule = module {
    single<FileStorageService> {
        Android// TODO: use
StorageService via DI: // TODO: use
StorageService via DI: FileStorageService(
            // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context = android// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context()
        )
    }
}
