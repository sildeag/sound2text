package com.sildeag.sound2text.service.recognizer

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

class CoroutineMicRecognizer {
    private val _recognizedText = MutableSharedFlow<String>()
    val recognizedText: Flow<String> = _recognizedText

    private val _micState = MutableStateFlow(false)
    val micState: StateFlow<Boolean> = _micState

    private var recognitionJob: Job? = null

    fun startMic(scope: CoroutineScope) {
        _micState.value = true
        recognitionJob = scope.launch {
            while (isActive) {
                delay(3000) // simulate recognition every 3 seconds
                _recognizedText.emit("Simulated speech at ${System.currentTimeMillis()}")
            }
        }
    }

    fun stopMic() {
        _micState.value = false
        recognitionJob?.cancel()
    }
}