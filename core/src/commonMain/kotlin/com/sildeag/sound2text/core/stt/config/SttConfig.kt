package com.sildeag.sound2text.core.stt.config

data class SttConfig(
    override val language: String,
    // Engine selection (source of truth)
    val engineName: String,
    // Desktop model location
    override val modelPath: String?,
    override val modelFile: String?,
    // Android model location
    override val androidModelDir: String?,
    override val androidModelFile: String?,
    // Audio parameters (runtime only)
    val sampleRate: Float = 16_000f
) : SttModelInfo {
    val engineId: Any
}