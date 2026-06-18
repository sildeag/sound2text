package com.sildeag.sound2text.core.audio

interface AudioRecorder {
    suspend fun start(onChunk: (ByteArray) -> Unit)
    suspend fun stop()
}
