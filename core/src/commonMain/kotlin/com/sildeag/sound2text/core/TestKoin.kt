package com.sildeag.sound2text.core

import com.sildeag.sound2text.di.core.coreModule
import org.koin.core.context.startKoin

fun initTestKoin() {
    startKoin {
        modules(
            coreModule, // shared logging
            testStorageModule // overrides FileStorageService
        )
    }
}
