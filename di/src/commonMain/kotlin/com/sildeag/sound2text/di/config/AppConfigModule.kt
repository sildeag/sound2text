package com.sildeag.sound2text.di.config

import com.sildeag.sound2text.core.config.AppSettings
import com.sildeag.sound2text.core.config.Environment
import com.sildeag.sound2text.core.settings.SettingsLoader
import org.koin.dsl.module

val configModule = module {

    // The current environment (Development, Staging, Production)
    single { Environment.Development }

    // Provide the SettingsLoader (platform-specific)
    single { SettingsLoader(get()) }

    // Provide AppSettings by calling load() on the loader
    single<AppSettings> { get<SettingsLoader>().load() }
}



/*
import com.sildeag.sound2text.config.VoskConfig
import org.koin.dsl.module

val configModule = module {
    single {
        VoskConfig(
            modelPath = "/path/to/vosk-model",
            sampleRate = 16000,
            maxAlternatives = 0,
            enableWords = true,
            enablePartialResults = true,
            logLevel = 0
        )
    }
}

 */
