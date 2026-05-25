package com.sildeag.sound2text.uidesktop.stt.vosk

// TODO: remove engine
import
import com.sildeag.sound2text.core.stt.SttResult

class VoskSttEngine(
    private val language: String,
    private val modelPath: String?,
    private val modelFile: String?,
    private val androidModelDir: String?,
    private val androidModelFile: String?,
    private val sampleRate: Float
) : SttEngine {
    override suspend fun start() {
        TODO("Not yet implemented")
    }

    override suspend fun stop() {
        TODO("Not yet implemented")
    }

    override suspend fun transcribe(bytes: ByteArray): SttResult {
        // Desktop Vosk logic here
        return TODO("Provide the return value")
    }

    override suspend fun recognizeOnce(): SttResult? {
        TODO("Not yet implemented")
    }
}
