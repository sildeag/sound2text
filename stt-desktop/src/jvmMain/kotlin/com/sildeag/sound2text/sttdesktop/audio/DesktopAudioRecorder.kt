package com.sildeag.sound2text.sttdesktop.audio

import com.sildeag.sound2text.core.stt.AudioRecorder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
class DesktopAudioRecorder : AudioRecorder {
    override suspend fun start(onChunk: (ByteArray) -> Unit) {
        withContext(Dispatchers.IO) {
            // TODO: Implement microphone capture and call onChunk with PCM data.
            // This is intentionally left as a concrete placeholder for your platform code.
        }
    }
    override suspend fun stop() {
        withContext(Dispatchers.IO) {
            // TODO: Stop and release microphone resources.
        }
    }
}
