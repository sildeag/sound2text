package com.sildeag.sound2text.di

import com.sildeag.sound2text.storage.StorageService
import org.koin.dsl.module
actual fun platformModules(contextProvider: Any?): List<Module> =
    listOf(
        module {
            // real JVM services
            single<AudioService> { AudioService() }
            single<StorageService> { StorageService() }
        }
    )
