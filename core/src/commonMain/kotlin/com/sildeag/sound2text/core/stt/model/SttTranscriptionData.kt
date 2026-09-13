package com.sildeag.sound2text.core.stt.model

data class SttTranscriptionData(
    val text: String,
    val confidence: Float? = null
)