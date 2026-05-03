package com.sildeag.sound2text.core.stt

data class SttConfig(
    val language: String = "en-US",
    val modelPath: String? = null, // Desktop: absolute or relative path
    val androidModelDir: String? = null, // Android: directory under filesDir
    val sampleRate: Float = 16_000f,
    val engineId: String = "vosk" // future engines: "whisper", "google", etc.
)

