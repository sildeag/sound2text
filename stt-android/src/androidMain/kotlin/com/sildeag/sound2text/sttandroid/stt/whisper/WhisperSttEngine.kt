package com.sildeag.sound2text.sttandroid.stt.whisper

import com.sildeag.sound2text.core.stt.engine.SttEngine

class WhisperSttEngine(
    private val modelPath: String,
    private val language: String?,
    private val onFinal: (String) -> Unit,
    private val onError: (String) -> Unit
) : SttEngine {

    override suspend fun start() {
        // Load whisper.cpp model here (JNI later)
        // For now, just a placeholder
        try {
            // TODO: load model
        } catch (e: Exception) {
            onError("Whisper start error: ${e.message}")
        }
    }

    override suspend fun processAudio(chunk: ByteArray) {
        try {
            // Whisper does not produce partials.
            // When JNI is added, call whisper.cpp here.
            onFinal("whisper transcription placeholder")
        } catch (e: Exception) {
            onError("Whisper audio error: ${e.message}")
        }
    }

    override suspend fun stop() {
        // Cleanup when JNI is added
    }
}
