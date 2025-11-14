package com.sildeag.sound2text.core.service.recognizer

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

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