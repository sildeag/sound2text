package com.sildeag.sound2text.core.stt

data class SttConfig(
    val language: String = "en-US",
    // Engine selection
    val engineName: String = "vosk",
    // Desktop model location
    val modelPath: String? = null, // directory or full path
    val modelFile: String? = null, // optional: specific file inside modelPath
    // Android model location
    val androidModelDir: String? = null, // directory under filesDir
    val androidModelFile: String? = null,
    // Audio parameters
    val sampleRate: Float = 16_000f
)

