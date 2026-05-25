package com.sildeag.sound2text.di.stt.vosk

// TODO: remove engine
importPlugin
// TODO: remove engine
importPlugin
import org.koin.core.qualifier.named
import org.koin.dsl.module

val voskModule = module {
    single<SttEnginePlugin>(named("vosk")) {
        VoskEnginePlugin()
    }
}
