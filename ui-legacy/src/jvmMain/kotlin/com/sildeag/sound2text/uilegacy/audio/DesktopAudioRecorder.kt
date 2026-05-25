package com.sildeag.sound2text.uilegacy.audio

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
class DesktopAudioRecorder {
    private val audioFlow = MutableSharedFlow<ShortArray>(extraBufferCapacity = 8)
    fun start(): Flow<ShortArray> {
        // TODO: hook into your existing DesktopAudioCapture logic
        return audioFlow
    }
    fun stop() {
        // TODO: stop capture
    }
}
