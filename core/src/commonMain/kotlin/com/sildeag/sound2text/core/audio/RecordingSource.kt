package com.sildeag.sound2text.core.audio

interface RecordingSource {
    fun start(onAudio: (ByteArray) -> Unit)
    fun stop()
}