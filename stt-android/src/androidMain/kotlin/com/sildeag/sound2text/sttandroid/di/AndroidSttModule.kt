package com.sildeag.sound2text.sttandroid.di

import com.sildeag.sound2text.core.stt.*
import com.sildeag.sound2text.sttandroid.engine.CreateSttAndroid
import com.sildeag.sound2text.sttandroid.engine.vosk.VoskPlugin
import com.sildeag.sound2text.sttandroid.engine.whisper.WhisperPlugin
import com.sildeag.sound2text.sttandroid.service.AndroidSttService
import org.koin.dsl.module

val androidSttModule = module {
    // 1. Register Android STT plugins
    single<List<SttEnginePlugin>> {
        listOf(
            VoskPlugin(),
            WhisperPlugin()
        )
    }
    // 2. Android-specific STT orchestrator
    single { CreateSttAndroid(get()) }
    // 3. Provide Android STT service
    single<SttService> {
        val config = get<SttConfig>()
        val createStt = get<CreateSttAndroid>()
        createStt.create(config)
    }
    // 4. Android audio capture (optional)
    // single { AndroidAudioCapture(context = get()) }
}