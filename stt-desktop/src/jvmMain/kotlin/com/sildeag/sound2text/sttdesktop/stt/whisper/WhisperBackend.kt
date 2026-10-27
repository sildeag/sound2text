package com.sildeag.sound2text.sttdesktop.stt.whisper

import com.sildeag.sound2text.core.stt.engine.*
import com.sildeag.sound2text.core.stt.model.SttResult
import com.sildeag.sound2text.core.stt.model.UnifiedBackendResult
import com.sildeag.sound2text.sttandroid.stt.whisper.WhisperSttEngine
import com.sildeag.sound2text.sttdesktop.stt.unified.UnifiedBackendRouter

class WhisperBackend(
    private val engine: WhisperSttEngine
) : UnifiedBackend {

    override suspend fun start() {
        engine.start()
    }

    override suspend fun process(chunk: ByteArray): UnifiedBackendRouter {
        return when (val result = engine.processAudio(chunk)) {
            is SttResult.Final -> {
                UnifiedBackendResult(result.text, true)
            }
            is SttResult.Partial -> {
                UnifiedBackendResult(result.text, false)
            }
            is SttResult.Error -> {
                UnifiedBackendResult(result.message, true)
            }
            else -> {
                // Handle any unexpected states gracefully
                UnifiedBackendResult("", false)
            }
        }
    }

    override suspend fun finish(): SttResult =
        engine.finish()

    override suspend fun stop() {
        engine.stop()
    }
}