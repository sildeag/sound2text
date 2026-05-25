package com.sildeag.sound2text.core.config

import kotlinx.serialization.Serializable
@Serializable
data class VoskConfig(
    val modelPath: String,
    val sampleRate: Int = 16000,
    val maxAlternatives: Int = 0,
    val enableWords: Boolean = true,
    val enablePartialResults: Boolean = true,
    val logLevel: Int = 0
) : SttEngineConfig
