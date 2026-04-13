package com.sildeag.sound2text.vosk.engine.vosk.jvm.di

import com.sildeag.sound2text.core.stt.SpeechToTextService
import com.sildeag.sound2text.sttdesktop.service.vosk.VoskSpeechService
import org.koin.dsl.module

val voskModule = module {
    single<SpeechToTextService> {
        VoskSpeechService(config = get())
    }
}