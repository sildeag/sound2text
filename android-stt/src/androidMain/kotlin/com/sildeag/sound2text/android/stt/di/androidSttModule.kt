package com.sildeag.sound2text.androidstt.di

import com.sildeag.sound2text.androidstt.AndroidSpeechService
import com.sildeag.sound2text.core.stt.SpeechToTextService
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf

val androidSttModule = module {
    singleOf<SpeechToTextService>(::AndroidSpeechService)
}