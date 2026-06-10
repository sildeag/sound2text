package com.sildeag.sound2text.featurerecording.recording

interface RecordingSource {
    fun start(onChunk: (ByteArray) -> Unit)
    fun stop()
}
