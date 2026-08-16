package com.sildeag.sound2text.core.audio

data class AudioChunk(
    val data: ByteArray,
    val timestampMs: Long
)