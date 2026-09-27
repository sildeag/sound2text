package com.sildeag.sound2text.featurerecording.recording



sealed class `RecordingState.txt` {
    object Idle : `RecordingState.txt`()
    object Starting : `RecordingState.txt`()
    object Stopping : `RecordingState.txt`()
    object Recording : `RecordingState.txt`()

    object Processing : `RecordingState.txt`()
    data class Error(val message: String) : `RecordingState.txt`()

}