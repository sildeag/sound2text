package com.sildeag.sound2text.stt.di

import com.sildeag.sound2text.stt.*

class JvmSttEngine(
    private val configProvider: () -> SttConfig = { SttConfig() }
) : SttEngine {
    override fun loadModel(config: SttConfig): SttService {
        // Load Vosk, Whisper-CPP, or other desktop model
        return JvmSttService(config)
    }
}
