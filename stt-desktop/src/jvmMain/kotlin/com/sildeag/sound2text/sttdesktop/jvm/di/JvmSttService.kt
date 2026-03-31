package com.sildeag.sound2text.sttdesktop.jvm.di

import com.sildeag.sound2text.sttdesktop.*
import com.sildeag.sound2text.sttdesktop.jvm.JvmSttService
import com.sildeag.sound2text.sttdesktop.jvm.VoskSttEngine
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.vosk.Model

val sttJvmModule = module {
    // --- Engine registrations -------------------------------------------------
    // Vosk engine
    single<SttEngine>(named("vosk")) {
        val config = get<SttConfig>()
        val model = Model(config.modelPath)
        VoskSttEngine(model) // your engine class
    }
    // Whisper-CPP engine (placeholder for future)
    single<SttEngine>(named("whisper")) {
        WhisperCppSttEngine() // implement later
    }
    // --- Platform STT service -------------------------------------------------
    // JvmSttService delegates to whichever engine is selected
    factory<SttService> {
        val config = get<SttConfig>()
        val engineName = config.engineName // from settings
        val engine = get<SttEngine>(named(engineName))
        JvmSttService(engine, config)
    }
}