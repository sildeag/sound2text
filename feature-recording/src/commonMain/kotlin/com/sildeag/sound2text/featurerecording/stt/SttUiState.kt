package com.sildeag.sound2text.featurerecording.stt

import com.sildeag.sound2text.featurerecording.recording.RecordingState

data class SttUiState(
    val recordingState: RecordingState = RecordingState.Idle,
    // ...
)