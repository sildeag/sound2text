package com.sildeag.sound2text.core.stt

data class SttConfig(
    override val language: String,
    override val sttEngine: String,
    val engineName: String,
    // Desktop model location
    override val modelPath: String?,
    override val modelFile: String?,
    // Android model location
    override val androidModelDir: String?,
    override val androidModelFile: String?,
    // Audio parameters
    val sampleRate: Float = 16_000f
) : SttModelInfo
