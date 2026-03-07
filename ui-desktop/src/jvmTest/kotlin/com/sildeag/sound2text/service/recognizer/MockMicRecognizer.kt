package com.sildeag.sound2text.service.recognizer

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MockMicRecognizer {
    private val _recognizedText = MutableSharedFlow<String>()
    val recognizedText: Flow<String> = _recognizedText
    private val _micState = MutableStateFlow(false)
    val micState: StateFlow<Boolean> = _micState
    suspend fun simulateSpeech(text: String) {
        _recognizedText.emit(text)
    }
    fun simulateMicStart() {
        _micState.value = true
    }
    fun simulateMicStop() {
        _micState.value = false
    }
}
