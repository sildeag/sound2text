package com.sildeag.sound2text.core.usecase

import com.sildeag.sound2text.core.stt.engine.SttEngine

class ProcessAudioUseCase(
    private val engine: SttEngine
) {
    suspend operator fun invoke(bytes: ByteArray) {
        return engine.processAudio(bytes)
    }
}
