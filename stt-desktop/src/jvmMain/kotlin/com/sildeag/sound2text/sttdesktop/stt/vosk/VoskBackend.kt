package com.sildeag.sound2text.sttdesktop.stt.vosk

import com.sildeag.sound2text.core.stt.engine.*
import com.sildeag.sound2text.core.stt.model.SttResult

class VoskBackend(
    private val engine: VoskSttEngine
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

    override fun startSession() {
        TODO("Not yet implemented")
    }

    override fun processSamples(chunk: ByteArray) {
        TODO("Not yet implemented")
    }

    override fun finalizeSession() {
        TODO("Not yet implemented")
    }
}