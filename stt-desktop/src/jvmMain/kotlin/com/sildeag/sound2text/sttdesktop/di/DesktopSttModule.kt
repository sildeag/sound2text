package com.sildeag.sound2text.sttdesktop.di

import com.sildeag.sound2text.core.stt.*
import com.sildeag.sound2text.sttdesktop.engine.CreateStt
import com.sildeag.sound2text.sttdesktop.engine.vosk.VoskPlugin
import com.sildeag.sound2text.sttdesktop.engine.whisper.WhisperPlugin
import com.sildeag.sound2text.sttdesktop.service.JvmSttService
import org.koin.dsl.module
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
