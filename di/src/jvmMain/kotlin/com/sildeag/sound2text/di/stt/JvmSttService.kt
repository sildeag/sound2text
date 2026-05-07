package com.sildeag.sound2text.di.stt

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttEngine
import com.sildeag.sound2text.core.stt.SttService
import com.sildeag.sound2text.sttdesktop.service.JvmSttService
import com.sildeag.sound2text.sttdesktop.engine.whisper.WhisperCppSttEngine
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