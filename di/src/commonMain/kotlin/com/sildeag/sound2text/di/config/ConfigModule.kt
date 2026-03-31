package com.sildeag.sound2text.di.config

import com.sildeag.sound2text.pdfdesktop.AppSettings
import org.koin.dsl.module

val configModule = module {

    // The current environment (Development, Staging, Production)
    single { Environment.Development }

    // The main application configuration object, loaded via expect/actual
    single<AppSettings> { SettingsLoader.load() }
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