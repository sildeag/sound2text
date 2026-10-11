package com.sildeag.sound2text.sttdesktop.stt.whisper

import com.sildeag.sound2text.core.stt.engine.*
import com.sildeag.sound2text.core.stt.model.SttResult
import com.sildeag.sound2text.sttandroid.stt.whisper.WhisperSttEngine

class WhisperBackend(
    private val engine: WhisperSttEngine
) : UnifiedBackend {

    override suspend fun start() {
        engine.start()
    }

    override suspend fun process(
        chunk: ByteArray
    ): UnifiedBackendResult {

        return when (
            val result = engine.processAudio(chunk)
        ) {
            is SttResult.Final ->
                UnifiedBackendResult(
                    result.text,
                    true
                )

            is SttResult.Partial ->
                UnifiedBackendResult(
                    result.text,
                    false
                )

            is SttResult.Error ->
                UnifiedBackendResult(
                    result.message,
                    true
                )


        }
    }

    override suspend fun finish(): String =
        engine.finish().text

    override suspend fun stop() {
        engine.stop()
    }
}