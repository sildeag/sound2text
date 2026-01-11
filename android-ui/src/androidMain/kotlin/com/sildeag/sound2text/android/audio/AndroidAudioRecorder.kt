package com.sildeag.sound2text.android.audio

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
class AndroidAudioRecorder {
    private val audioFlow = MutableSharedFlow<ShortArray>(extraBufferCapacity = 8)
    fun start(): Flow<ShortArray> {
        // TODO: hook into AudioRecord or your existing Android capture
        return audioFlow
    }
    fun stop() {
        // TODO: stop capture
    }
}