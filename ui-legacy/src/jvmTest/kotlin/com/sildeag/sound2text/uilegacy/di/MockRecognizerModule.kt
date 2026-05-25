package com.sildeag.sound2text.uilegacy.di

import com.sildeag.sound2text.service.recognizer.Recognizer
import org.koin.dsl.module

val mockRecognizerModule = module {
    single<Recognizer> { MockMicRecognizer() }
}
