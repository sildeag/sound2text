package com.sildeag.sound2text.di.core.stt

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttEngineFactory
import com.sildeag.sound2text.core.stt.SttService
import org.koin.dsl.module
import org.koin.core.qualifier.named
val sttJvmModule = module {
    single<SttEngineFactory>(named("vosk")) { VoskEngineFactory() }
    single<SttEngineFactory>(named("whisper"))
    { WhisperCppEngineFactory() }
    factory<SttService> {
        val config = get<SttConfig>()
        val engineName = config.engineName
        get<SttEngineFactory>(named(engineName)).loadModel(config)
    }
}