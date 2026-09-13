package com.sildeag.sound2text.core.stt.services

import com.sildeag.sound2text.core.stt.engine.SttEngine
import com.sildeag.sound2text.core.stt.model.SttResult
class SttService(
    private val engine: SttEngine
) {
    suspend fun transcribe(chunk: ByteArray): SttResult =
        engine.processAudio(chunk)
}
