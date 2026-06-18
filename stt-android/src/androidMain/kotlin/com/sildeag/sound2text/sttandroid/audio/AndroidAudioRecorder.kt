package com.sildeag.sound2text.sttandroid.audio

import android.content.Context
import com.sildeag.sound2text.core.stt.AudioRecorder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
class AndroidAudioRecorder(
    private val context: Context
) : AudioRecorder {
    override suspend fun start(onChunk: (ByteArray) -> Unit) {
        withContext(Dispatchers.IO) {
            // TODO: Implement AudioRecord, read PCM, and call onChunk with audio data.
        }
    }
    override suspend fun stop() {
        withContext(Dispatchers.IO) {
            // TODO: Stop and release AudioRecord.
        }
    }
}