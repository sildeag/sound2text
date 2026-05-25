package com.sildeag.sound2text.platform

import org.koin.dsl.module

actual fun platformModules() = listOf(
    module {
        single { SettingsLoader.load() }
        single { PlatformEnvironment() }
        // Android services…
    }
)
