package com.sildeag.sound2text.core.stt

data class SttTranscriptionData(
    val text: String,
    val confidence: Float? = null,
    val engineName: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)
