package com.sildeag.sound2text.featurerecording.recording

sealed class RecordingState {
    object Idle : RecordingState()
    object Starting : RecordingState()
    object Recording : RecordingState()
    object Processing : RecordingState()
    data class Error(val message: String) : RecordingState()
}