package com.sildeag.sound2text.featurerecording.stt

import com.sildeag.sound2text.featurerecording.recording.RecordingState

data class SttUiState(
    val recordingState: RecordingState = RecordingState.Idle,
    val partialText: String = "",
    val finalText: String = "",
    val isSaving: Boolean = false,
    val errorMessage: String? = null
)
