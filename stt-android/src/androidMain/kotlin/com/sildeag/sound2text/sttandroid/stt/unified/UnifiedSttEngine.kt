package com.sildeag.sound2text.sttandroid.stt.unified

import com.sildeag.sound2text.core.stt.engine.BaseSttEngine
import com.sildeag.sound2text.core.stt.engine.UnifiedBackend
import com.sildeag.sound2text.core.stt.model.SttResult

class UnifiedSttEngine(
    private val backend: UnifiedBackend,
    private val onPartial: (String) -> Unit,
    private val onFinal: (String) -> Unit,
    private val onError: (String) -> Unit
) : BaseSttEngine() {
    override suspend fun start() {
        try {
            backend.start()
        } catch (e: Exception) {
            onError("Unified start error: ${e.message}")
        }
    }
    override suspend fun processAudio(chunk: ByteArray): SttResult {
        return try {
            val result = backend.process(chunk)
            if (result.isFinal) {
                onFinal(result.text)
                SttResult.Final(result.text)
            } else {
                onPartial(result.text)
                SttResult.Partial(result.text)
            }
        } catch (e: Exception) {
            val msg = "Unified audio error: ${e.message}"
            onError(msg)
            SttResult.Error(msg)
        }
    }
    override suspend fun finish(): SttResult {
        return try {
            val text = backend.finish()
            onFinal(text)
            SttResult.Final(text)
        } catch (e: Exception) {
            val msg = "Unified finish error: ${e.message}"
            onError(msg)
            SttResult.Error(msg)
        }
    }
    override suspend fun stop() {
        backend.stop()
    }
}
