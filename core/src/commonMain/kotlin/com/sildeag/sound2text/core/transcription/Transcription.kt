package com.sildeag.sound2text.core.transcription

data class Transcription(
    val id: String,
    val text: String,
    val createdAt: Long,
    val durationMs: Long,
    val source: String, // "mic", "file", "pdf"
    val audioPath: String? // optional: null for mic input
)
