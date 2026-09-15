package com.sildeag.sound2text.sttandroid.stt.whisper

import com.sildeag.sound2text.core.stt.engine.SttEngine
import com.sildeag.sound2text.core.stt.model.SttResult

class WhisperSttEngine(
    private val modelPath: String,
    private val language: String?,
    private val onFinal: (String) -> Unit,
    private val onError: (String) -> Unit
) : SttEngine {

    override suspend fun start() {
        try {
            // TODO: load whisper.cpp model via JNI
        } catch (e: Exception) {
            onError("Whisper start error: ${e.message}")
        }
    }

    override suspend fun processAudio(chunk: ByteArray): SttResult {
        return try {
            // Placeholder until JNI is added
            val text = "whisper transcription placeholder"
            onFinal(text)
            SttResult.Final(text)
        } catch (e: Exception) {
            val msg = "Whisper audio error: ${e.message}"
            onError(msg)
            SttResult.Error(msg)
        }
    }

    override suspend fun stop() {
        // Cleanup when JNI is added
    }
}
