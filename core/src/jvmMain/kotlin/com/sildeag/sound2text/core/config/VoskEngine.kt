package com.sildeag.sound2text.core.config

// TODO: remove engine
import

class VoskEngine(
    val modelPath: String,
    val sampleRate: Int = 16000,
    val maxAlternatives: Int = 0,
    val enableWords: Boolean = true,
    val enablePartialResults: Boolean = true,
    val logLevel: Int = 0,
    val language: SttLanguage,
    val ffmpeg: FfmpegSettings
) : SttEngine {
    override suspend fun start() {
        TODO("Not yet implemented")
    }

    override suspend fun stop() {
        TODO("Not yet implemented")
    }

    override fun transcribe(input: ByteArray): String {
        // TODO: Implement Vosk transcription using these fields
        return ""
    }
}
