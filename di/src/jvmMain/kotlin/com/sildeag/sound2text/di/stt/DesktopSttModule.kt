package com.sildeag.sound2text.di.stt

import com.sildeag.sound2text.sttdesktop.service.SttService
import com.sildeag.sound2text.sttdesktop.service.vosk.VoskModelFactory
import com.sildeag.sound2text.sttdesktop.service.vosk.VoskSttService
import org.koin.core.module.Module
import org.koin.dsl.module

fun desktopSttModule(config: SttConfig): Module = module {
    single { config }
    single {
        VoskModelFactory.loadModel(get())
    }
    single<SttService> {
        VoskSttService(
            model = get(),
            config = get()
        )
    }
}



/*
import com.sildeag.sound2text.core.stt.*
import com.sildeag.sound2text.sttdesktop.engine.CreateStt
import com.sildeag.sound2text.sttdesktop.engine.vosk.VoskPlugin
import com.sildeag.sound2text.sttdesktop.engine.whisper.WhisperPlugin
import org.koin.dsl.module
import sun.nio.ch.DefaultSelectorProvider.get

val desktopSttModule = module {
    // 1. Register all desktop STT plugins
    single<List<SttEnginePlugin>> {
        listOf(
            VoskPlugin(),
            WhisperPlugin()
        )
    }
    // 2. Create the STT factory/orchestrator
    single { CreateStt(get()) }
    // 3. Provide the STT service (engine-agnostic)
    single<SttService> {
        val config = get<SttConfig>()
        val createStt = get<CreateStt>()
        createStt.create(config)
    }
    // 4. Optional: audio capture components
    // (only if you want DI for audio)
    // single { DesktopAudioCapture() }
    // single { DesktopSoundPlayer() }
}
