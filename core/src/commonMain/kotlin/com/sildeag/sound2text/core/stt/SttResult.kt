package com.sildeag.sound2text.core.stt

/**
 * Unified STT result type returned by single-shot transcription.
 * All engines (Vosk, Android Speech API, future engines) must return this.
 */
data class SttResult(
    val transcript: String, // Final recognized text
    val confidence: Float, // Engine-specific confidence score (0.0–1.0)
    val engineName: String, // Identifier from SttEnginePlugin.engineName
    val timestamp: Long // Unix time when transcription completed
)
