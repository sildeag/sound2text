package com.sildeag.sound2text.di

import android.content.Context
import com.sildeag.sound2text.storage.StorageService
import com.sildeag.sound2text.storage.android.AndroidStorageService
import org.koin.dsl.module
actual fun platformModules(contextProvider: Any?): List<Module> =
    listOf(
        module {
            // real Android services
            single<AudioService> { AndroidAudioService(contextProvider as
                    android.content.Context) }
            single<StorageService>
            { AndroidStorageService(contextProvider as Context) }
        }
    )
