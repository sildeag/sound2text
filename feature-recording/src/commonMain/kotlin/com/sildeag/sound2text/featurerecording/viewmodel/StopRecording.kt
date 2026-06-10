package com.sildeag.sound2text.featurerecording.viewmodel

fun stopRecording() = viewModelScope.launch {
    try {
        recognizer.stop()
        _uiState.update { it.copy(recordingState =
            RecordingState.Processing) }
    } catch (t: Throwable) {
        onError(t.message ?: "Error stopping recognizer")
    }
}