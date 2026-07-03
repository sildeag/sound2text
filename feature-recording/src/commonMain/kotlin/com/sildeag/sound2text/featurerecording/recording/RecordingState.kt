package com.sildeag.sound2text.featurerecording.recording

import com.sildeag.sound2text.core.stt.SttResult

sealed class RecordingState {
    object Idle : RecordingState()
    object Starting : RecordingState()
    object Stopping : RecordingState()
    object Recording : RecordingState()

    object Processing : RecordingState()
    data class Error(val message: String) : RecordingState()

}