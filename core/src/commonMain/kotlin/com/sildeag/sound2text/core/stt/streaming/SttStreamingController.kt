package com.sildeag.sound2text.core.stt.streaming
import com.sildeag.sound2text.core.stt.engine.SttEngine
import com.sildeag.sound2text.core.stt.model.SttResult
class SttStreamingController(
    private val engine: SttEngine,
    private val callback: (SttResult) -> Unit
) {
    suspend fun start() {
        try {
            engine.start()
        } catch (e: Exception) {
            callback(SttResult.Error("Streaming start failed: ${e.message}"))
        }
    }
    suspend fun feed(chunk: ByteArray) {
        try {
            engine.processAudio(chunk)
        } catch (e: Exception) {
            callback(SttResult.Error("Streaming feed failed: ${e.message}"))
        }
    }
    suspend fun stop() {
        engine.stop()
    }
    suspend fun finish() {
        try {
            engine.finish()
        } catch (e: Exception) {
            callback(SttResult.Error("Streaming finish failed: ${e.message}"))
        }
    }
}