package com.sildeag.sound2text.di.stt.vosk

import com.sildeag.sound2text.core.stt.SttEnginePlugin
import com.sildeag.sound2text.core.stt.plugins.VoskEnginePlugin
import org.koin.core.qualifier.named
import org.koin.dsl.module

val voskModule = module {
    single<SttEnginePlugin>(named("vosk")) {
        VoskEnginePlugin()
    }
}
