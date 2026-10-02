package com.sildeag.sound2text.core.stt.services

import com.sildeag.sound2text.core.stt.engine.BaseSttEngine
import com.sildeag.sound2text.core.stt.model.SttResult
import kotlinx.coroutines.flow.Flow

class SttService(
    private val engine: BaseSttEngine
) {
    suspend fun transcribe(audio: Flow<ShortArray>): SttResult {
        engine.start()

        audio.collect { chunk ->
            val bytes = chunk.toByteArray()
            // We ignore the returned SttResult because partials/finals
            // are handled by callbacks in the engine.
            engine.processAudio(bytes)
        }

        // The final result comes from finish()
        return engine.finish()
    }

    suspend fun stop() {
        engine.stop()
    }
}

private fun ShortArray.toByteArray(): ByteArray {
    val buffer = ByteArray(size * 2)
    var i = 0
    for (sample in this) {
        buffer[i++] = (sample.toInt() and 0xFF).toByte()
        buffer[i++] = ((sample.toInt() shr 8) and 0xFF).toByte()
    }
    return buffer
}
