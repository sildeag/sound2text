package com.sildeag.sound2text.sttdesktop.stt.unified
import com.sildeag.sound2text.core.stt.engine.BaseSttEngine
import com.sildeag.sound2text.core.stt.engine.UnifiedBackend
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
    // Now cleanly overrides the Unit-returning parent method
    override suspend fun processAudio(chunk: ByteArray) {
        try {
            val result = backend.process(chunk)
            if (result.isFinal) {
                onFinal(result.text)
            } else {
                onPartial(result.text)
            }
        } catch (e: Exception) {
            onError("Unified audio error: ${e.message}")
        }
    }
    override suspend fun finish() {
        try {
            val textResult = backend.finish()
            onFinal(textResult)
        } catch (e: Exception) {
            onError("Unified finish error: ${e.message}")
        }
    }
    override suspend fun stop() {
        backend.stop()
    }
}
