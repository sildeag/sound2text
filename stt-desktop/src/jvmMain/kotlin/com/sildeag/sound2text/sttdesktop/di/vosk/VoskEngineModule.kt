package com.sildeag.sound2text.vosk.engine.vosk.jvm.di

import com.sildeag.sound2text.core.stt.SpeechToTextService
import com.sildeag.sound2text.vosk.engine.stt.VoskLiveRecognizer
import com.sildeag.sound2text.sttdesktop.service.vosk.VoskSpeechService
import org.koin.dsl.module
val voskEngineModule = module {
    // Low-level recognizer
    single { VoskLiveRecognizer() }
    // High-level STT service
    single<SpeechToTextService> { VoskSpeechService(get()) }
}