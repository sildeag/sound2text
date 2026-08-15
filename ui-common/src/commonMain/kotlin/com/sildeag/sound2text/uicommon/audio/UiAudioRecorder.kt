package com.sildeag.sound2text.uicommon.audio

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.sildeag.sound2text.core.audio.AudioRecorder
import com.sildeag.sound2text.core.audio.toAmplitude
import com.sildeag.sound2text.uicommon.models.RecordingState
import kotlinx.coroutines.flow.update

class UiAudioRecorder(
    private val platformRecorder: AudioRecorder
) {
    private val _state = MutableStateFlow<RecordingState>(RecordingState.Idle)
    val state: StateFlow<RecordingState> = _state

    suspend fun start() {
        _state.value = RecordingState.Starting

        platformRecorder.start { bytes ->
            val amp = bytes.toAmplitude()
            _state.value = RecordingState.Recording(amplitude = amp)
        }

        // Initial recording state with amplitude = 0f
        _state.value = RecordingState.Recording(amplitude = 0f)
    }

    suspend fun stop() {
        _state.value = RecordingState.Stopping
        platformRecorder.stop()
        _state.value = RecordingState.Idle
    }

    fun updateAmplitude(value: Float) {
        _state.update {
            if (it is RecordingState.Recording) {
                it.copy(amplitude = value)
            } else it
        }
    }

    fun updateWaveform(values: List<Float>) {}
}

