package com.sildeag.sound2text.featurerecording.viewmodel

fun onPartial(text: String) {
    _uiState.update { it.copy(partialText = text) }
}