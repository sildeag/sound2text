package com.sildeag.sound2text.core.stt.engine

import com.sildeag.sound2text.core.stt.model.SttResult

class UnifiedSttEngine(
    private val engines: List<SttEngine>
) : SttEngine {

    override suspend fun start() {
        engines.forEach { it.start() }
    }

    override suspend fun stop() {
        engines.forEach { it.stop() }
    }

    override suspend fun processAudio(chunk: ByteArray): SttResult {
        // Try engines in order: Whisper (accurate), Vosk (fast), etc.
        for (engine in engines) {
            val result = engine.processAudio(chunk)

            // If any engine produces a final result, return it immediately
            if (result is SttResult.Final) {
                return result
            }
        }

        // If none produce a final result, return the best partial
        return SttResult.Partial("")
    }
}
