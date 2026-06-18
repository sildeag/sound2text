package com.sildeag.sound2text.di

import com.sildeag.sound2text.storage.StorageService
import com.sildeag.sound2text.storage.android.AndroidStorageService
import org.koin.dsl.module
actual fun platformModules(
    listOf(
        module {
            // real Android services
            single<AudioService> { AndroidAudioService(
 }
            single<StorageService>
            { AndroidStorageService( }
    )
