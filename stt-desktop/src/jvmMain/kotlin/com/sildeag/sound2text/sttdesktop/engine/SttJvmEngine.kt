package com.sildeag.sound2text.sttdesktop.engine

import com.sildeag.sound2text.core.stt.SttConfig
// TODO: remove engine
import
import com.sildeag.sound2text.sttdesktop.service.SttService

class SttJvmEngine(
    private val configProvider: () -> SttConfig = { SttConfig() }
) : SttEngine {
    override fun loadModel(config: SttConfig): SttService {
        // Load Vosk, Whisper-CPP, or other desktop model
        return SttJvmService(config)
    }
}
