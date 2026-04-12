package com.sildeag.sound2text.sttandroid.di


import com.sildeag.sound2text.stt.*
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.vosk.android.SpeechService
import org.vosk.android.Recognizer
import org.vosk.Model

val SttAndroidModule = module {
    // --- Engine registrations -------------------------------------------------
    // Vosk engine for Android
    single<SttEngine>(named("vosk")) {
        val config = get<SttConfig>()
        val model = Model(config.modelPath)
        VoskAndroidSttEngine(model) // your Android engine class
    }
    // Whisper-CPP engine placeholder
    single<SttEngine>(named("whisper")) {
        WhisperCppAndroidSttEngine() // implement later
    }
    // --- Platform STT service -------------------------------------------------
    // AndroidSttService delegates to whichever engine is selected
    factory<SttService> {
        val config = get<SttConfig>()
        val engineName = config.engineName
        val engine = get<SttEngine>(named(engineName))
        AndroidSttService(engine, config)
    }
}