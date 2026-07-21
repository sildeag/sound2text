package com.sildeag.sound2text.uicommon.state


data class SttUiState(
    val recordingState: RecordingState = RecordingState.Idle,
    val partialText: String = "",
    val finalText: String = "",
    val isSaving: Boolean = false,
    val errorMessage: String? = null
)