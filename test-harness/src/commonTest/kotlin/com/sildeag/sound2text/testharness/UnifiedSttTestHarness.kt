package com.sildeag.sound2text.testharness

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttEngine
import com.sildeag.sound2text.core.stt.SttResult
import com.sildeag.sound2text.core.stt.UnifiedEngineRegistry

class UnifiedSttTestHarness(
    private val registry: UnifiedEngineRegistry
) {
    /**
     * Loads the correct STT engine using the unified registry.
     * This works for both JVM and Android engines.
     */
    fun loadEngine(config: SttConfig): SttEngine {
        return registry.loadEngine(config)
    }
    /**
     * Runs a single-shot transcription test.
     * This MUST use transcribe(), not processAudio().
     */
    suspend fun runSingleShot(
        config: SttConfig,
        audio: ByteArray
    ): SttResult {
        val engine = loadEngine(config)
        return engine.transcribe(audio)
    }
    /**
     * Runs a streaming test.
     * This uses processAudio() and expects callbacks.
     */
    suspend fun runStreaming(
        config: SttConfig,
        audioChunks: List<ByteArray>,
        onPartial: (String) -> Unit,
        onFinal: (String) -> Unit
    ) {
        val engine = loadEngine(config)
        if (engine !is StreamingSttEngine) {
            error("Engine ${config.engineName} does not support streaming")
        }
        engine.startStreaming(onPartial, onFinal)
        audioChunks.forEach { chunk ->
            engine.processAudio(chunk)
        }
        engine.stopStreaming()
    }
}
