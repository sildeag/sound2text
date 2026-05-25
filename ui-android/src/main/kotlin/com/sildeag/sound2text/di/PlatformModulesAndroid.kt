package com.sildeag.sound2text.di

import android.content.// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
import com.sildeag.sound2text.storage.StorageService
import com.sildeag.sound2text.storage.android.AndroidStorageService
import org.koin.dsl.module
actual fun platformModules(// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: ContextProvider: Any?): List<Module> =
    listOf(
        module {
            // real Android services
            single<AudioService> { AndroidAudioService(// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: ContextProvider as
                    android.content.// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context) }
            single<StorageService>
            { AndroidStorageService(// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: ContextProvider as // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context) }
        }
    )
