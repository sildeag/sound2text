package com.sildeag.sound2text.featurerecording.storage

fun saveFinalText() = viewModelScope.launch {
    val text = uiState.value.finalText
    if (text.isBlank()) return@launch
    _uiState.update { it.copy(isSaving = true) }
    try {
        storage.saveTranscript(text)
        _uiState.update { it.copy(isSaving = false) }
    } catch (t: Throwable) {
        _uiState.update {
            it.copy(
                isSaving = false,
                errorMessage = t.message ?: "Error saving transcript"
            )
        }
    }
}
