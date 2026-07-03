package com.sildeag.sound2text.di.stt

import com.sildeag.sound2text.core.stt.SttStreamingController
import com.sildeag.sound2text.core.stt.SttEngine
import com.sildeag.sound2text.core.stt.SttEnginePlugin
import org.koin.dsl.module

val sttModule = module {
    // Engine plugin registry
    single<SttEnginePlugin> { VoskEnginePlugin() }
    // Engine factory
    single<SttEngine> { SttEngine(get(), get()) }
    // Controller
    single { SttStreamingController(get(), get(), get()) }
    // Wizard
    single { SttWizardViewModel(get(), get()) }
}
/*
val sttModule = module {
    single<SttEngine> {
        CombinedEngine(
            partialEngine = VoskEngine(),
            finalEngine = WhisperEngine()
        )
    }
}

 */