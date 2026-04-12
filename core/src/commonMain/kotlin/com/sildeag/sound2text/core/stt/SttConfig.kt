package com.sildeag.sound2text.core.stt

data class SttConfig(
    val language: String,
    val engineName: String,
    val modelPath: String,
    val modelFile: String
)
