package com.sildeag.sound2text.core.di

import com.sildeag.sound2text.core.config.AppEnvironment
import com.sildeag.sound2text.core.config.Environment
import com.sildeag.sound2text.core.service.recognizer.MockRecognizer
import com.sildeag.sound2text.core.service.recognizer.Recognizer
import com.sildeag.sound2text.core.service.recognizer.VoskRecognizerImpl
import org.koin.dsl.module

val recognizerModule = module {
    factory<Recognizer> {
        when (AppEnvironment.current) {
            Environment.TEST -> MockRecognizer()
            else -> VoskRecognizerImpl(
                modelPath = "models/vosk-model-small-en-us-0.15",
                logger = get()
            )
        }
    }
}
