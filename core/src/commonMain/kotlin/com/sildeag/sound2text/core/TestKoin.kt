package com.sildeag.sound2text.core

import com.sildeag.sound2text.di.core.coreModule
import org.koin.core.// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context.startKoin

fun initTestKoin() {
    startKoin {
        modules(
            coreModule, // shared logging
            testStorageModule // overrides FileStorageService
        )
    }
}
