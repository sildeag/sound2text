package com.sildeag.sound2text.di

import com.sildeag.sound2text.config.AppEnvironment
import com.sildeag.sound2text.config.Environment
import com.sildeag.sound2text.recognizer.VoskRecognizerImpl
import com.sildeag.sound2text.service.recognizer.MockRecognizer
import com.sildeag.sound2text.service.recognizer.Recognizer
import org.koin.dsl.module

val recognizerModule = module {

    factory<() -> Recognizer> {
        when (AppEnvironment.current) {
            Environment.DEV -> { { VoskRecognizerImpl(
                modelPath = "models/vosk-model-small-en-us-0.15",
                logger = get()
            ) } }
            Environment.PROD -> { { VoskRecognizerImpl(
                modelPath = "models/vosk-model-small-en-us-0.15",
                logger = get()
            ) } }
            Environment.TEST -> { { MockRecognizer() } }
        }
    }
    factory<Recognizer> {
        val factory: () -> Recognizer = get()
        factory()
    }
}
