package com.sildeag.sound2text.core.usecase

import com.sildeag.sound2text.core.stt.engine.SttEngine
import com.sildeag.sound2text.core.stt.model.SttResult

class ProcessAudioUseCase(
    private val engine: SttEngine
) {
    suspend operator fun invoke(bytes: ByteArray): SttResult {
        return engine.processAudio(bytes)
    }
}
