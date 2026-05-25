package com.sildeag.sound2text.di

import com.sildeag.sound2text.core.storage.StorageService
import org.koin.dsl.module

// You can add an actual implementation in commonTest (or jvmTest /
// androidTest) that overrides the production one.

// This single fake actual is shared across desktop tests and Android tests,
// because both depend on core/commonTest.

actual fun platformModules(// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: ContextProvider: Any?): List<Module> =
    listOf(
        module {
            // fake services for testing
            single<AudioService> { FakeAudioService() }
            single<StorageService> { FakeStorageService() }
        }
    )
