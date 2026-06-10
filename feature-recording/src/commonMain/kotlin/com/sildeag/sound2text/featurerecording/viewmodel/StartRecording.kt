package com.sildeag.sound2text.featurerecording.viewmodel

fun startRecording() = viewModelScope.launch {
    try {
        recognizer.start(
            onPartial = { text -> onPartial(text) },
            onFinal = { text -> onFinal(text) },
            onError = { msg -> onError(msg) }
        )
        _uiState.update { it.copy(recordingState =
            RecordingState.Recording) }
    } catch (t: Throwable) {
        onError(t.message ?: "Unknown error")
    }
}
