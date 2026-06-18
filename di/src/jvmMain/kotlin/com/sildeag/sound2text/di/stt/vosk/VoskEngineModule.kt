package com.sildeag.sound2text.di.stt.vosk

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.sttdesktop.service.vosk.VoskModelFactory
import com.sildeag.sound2text.sttdesktop.service.vosk.VoskSttService
import org.koin.dsl.module

fun voskEngineModule(config: SttConfig) = module {

    // Provide config
    single { config }

    // Provide Vosk model
    single {
        VoskModelFactory.loadModel(get())
    }

    // Provide STT service
    single<SttService> {
        VoskSttService(
            model = get(),
            config = get()
        )
    }
}
