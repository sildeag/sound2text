package com.sildeag.sound2text.uicommon.viewmodels

import com.sildeag.sound2text.uicommon.audio.UiAudioRecorder
import com.sildeag.sound2text.uicommon.state.SoundState
import com.sildeag.sound2text.uicommon.mappers.RecordingUiMapper
import com.sildeag.sound2text.uicommon.models.RecordingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
class RecordingViewModel(
    private val recorder: UiAudioRecorder
) {
    val state: StateFlow<RecordingState> = recorder.state

    fun start() {
        recorder.start()
    }

    fun stop() {
        recorder.stop()
    }

    fun updateAmplitude(value: Float) {
        recorder.updateAmplitude(value)
    }

    fun updateWaveform(values: List<Float>) {
        recorder.updateWaveform(values)
    }
}

