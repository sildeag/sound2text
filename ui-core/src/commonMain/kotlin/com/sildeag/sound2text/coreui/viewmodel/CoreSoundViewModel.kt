package com.sildeag.sound2text.coreui.viewmodel

import com.sildeag.sound2text.coreui.sound.SoundEngine
import com.sildeag.sound2text.coreui.state.SttState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
/**
 * Pure logic ViewModel for sound recording + transcription.
 * No Compose, no platform code, no UI dependencies.
 */
class CoreSoundViewModel(
    private val scope: CoroutineScope,
    private val soundEngine: SoundEngine // your abstraction for recording + STT
) {
    // --- State ---------------------------------------------------------------
    private val _state: MutableStateFlow<SttState> =
        MutableStateFlow(SttState.Idle)

    val state: StateFlow<SttState> = _state.asStateFlow()
    private val _transcript = MutableStateFlow("")
    val transcript: StateFlow<String> = _transcript.asStateFlow()
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    // --- Public API  ----------------------------------------------------------
    fun startRecording() {
        if (_state.value != SttState.Idle) return

        scope.launch {
            try {
                soundEngine.startRecording(
                    onPartial = { _transcript.value = it },
                    onFinal = {
                        _transcript.value = it
                        _state.value = SttState.Finished
                    }
                )
                _state.value = SttState.Recording
            } catch (t: Throwable) {
                _state.value = SttState.Error(t.message ?: "Unknown error")
            }
        }
    }

    fun stopRecording() {
        if (_state.value != SttState.Recording) return
        scope.launch {
            try {
                soundEngine.stopRecording()
                _state.value = SttState.Finished
            } catch (t: Throwable) {
                _error.value = t.message
                _state.value = SttState.Error(t.message ?: "Unknown error")
            }
        }
    }
    fun reset() {
        _state.value = SttState.Idle
        _transcript.value = ""
        _error.value = null
    }
}
