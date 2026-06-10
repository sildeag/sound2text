package com.sildeag.sound2text.featurerecording.viewmodel

import com.sildeag.sound2text.featurerecording.recording.RecordingState

fun onFinal(text: String) {
    _uiState.update {
        it.copy(
            finalText = text,
            recordingState = RecordingState.Idle
        )
    }
}
