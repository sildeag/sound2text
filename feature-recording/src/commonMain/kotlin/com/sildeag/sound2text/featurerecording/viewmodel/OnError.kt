package com.sildeag.sound2text.featurerecording.viewmodel

import com.sildeag.sound2text.featurerecording.recording.RecordingState

fun onError(message: String) {
    _uiState.update {
        it.copy(
            recordingState = RecordingState.Error(message),
            errorMessage = message
        )
    }
}
