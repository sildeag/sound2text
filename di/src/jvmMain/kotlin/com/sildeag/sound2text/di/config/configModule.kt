package com.sildeag.sound2text.di.config

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
