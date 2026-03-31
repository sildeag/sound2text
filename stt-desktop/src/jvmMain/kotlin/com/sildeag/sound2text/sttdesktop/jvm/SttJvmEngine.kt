package com.sildeag.sound2text.sttdesktop.di

import com.sildeag.sound2text.sttdesktop.*

class JvmSttEngine(
    private val configProvider: () -> SttConfig = { SttConfig() }
) : SttEngine {
    override fun loadModel(config: SttConfig): SttService {
        // Load Vosk, Whisper-CPP, or other desktop model
        return JvmSttService(config)
    }
}
