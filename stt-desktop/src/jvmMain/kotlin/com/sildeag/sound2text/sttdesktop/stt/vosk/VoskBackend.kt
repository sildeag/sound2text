package com.sildeag.sound2text.sttdesktop.stt.vosk
import com.sildeag.sound2text.core.stt.engine.UnifiedBackend
import com.sildeag.sound2text.core.stt.engine.BaseSttEngine
import com.sildeag.sound2text.core.stt.model.SttResult
import com.sildeag.sound2text.core.stt.model.UnifiedBackendResult

class VoskBackend(
    private val engine: BaseSttEngine
) : UnifiedBackend {
    override suspend fun start() {
        engine.start()
    }
    override suspend fun process(chunk: ByteArray): UnifiedBackendResult {
        return when (val result = engine.processAudio(chunk)) {
            is SttResult.Final -> UnifiedBackendResult(result.text, true)
            is SttResult.Partial -> UnifiedBackendResult(result.text, false)
            is SttResult.Error -> UnifiedBackendResult(result.message, true)

        }
    }
    override suspend fun finish(): String {
        return when (val result = engine.finish()) {
            is SttResult.Final -> result.text
            is SttResult.Partial -> result.text
            is SttResult.Error -> result.message
        }
    }
    override suspend fun stop() {
        engine.stop()
    }
}