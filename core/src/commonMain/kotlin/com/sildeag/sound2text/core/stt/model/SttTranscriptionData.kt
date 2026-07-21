package com.sildeag.sound2text.core.stt.model

data class SttTranscriptionData(
    val text: String,
    val isFinal: Boolean,
    val confidence: Float? = null,
    val engineName: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)