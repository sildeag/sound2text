package com.sildeag.sound2text.uicommon.viewmodels

import com.sildeag.sound2text.uicommon.state.SttUiState
import com.sildeag.sound2text.uicommon.state.SttEvent
import com.sildeag.sound2text.uicommon.state.SttAction
import com.sildeag.sound2text.core.stt.SttController
import com.sildeag.sound2text.uicommon.models.UiRecording
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class SttViewModel(
    private val controller: SttController,
    private val recorder: UiAudioRecorder
) {
    private val scope = CoroutineScope(Dispatchers.Default)
    private val _state = MutableStateFlow(SttUiState())
    val state: StateFlow<SttUiState> = _state
    private val _actions = MutableStateFlow<SttAction?>(null)
    val actions: StateFlow<SttAction?> = _actions
    fun onEvent(event: SttEvent) {
        when (event) {
            SttEvent.StartRecording -> startRecording()
            SttEvent.StopRecording -> stopRecording()
            SttEvent.CancelRecording -> cancelRecording()
            SttEvent.ApplyPartial -> applyPartial()
            SttEvent.ApplyFinal -> applyFinal()
        }
    }
    private fun startRecording() {
        scope.launch {
            recorder.start()
            controller.start()
            _state.update { it.copy(isRecording = true, partial = "",
                finalText = "") }
        }
    }
    private fun stopRecording() {
        scope.launch {
            recorder.stop()
            controller.stop()
            _state.update { it.copy(isRecording = false) }
        }
    }
    private fun cancelRecording() {
        scope.launch {
            recorder.cancel()
            controller.cancel()
            _state.update { SttUiState() }
        }
    }
    private fun applyPartial() {
        val partial = controller.partialText
        _state.update { it.copy(partial = partial) }
    }
    private fun applyFinal() {
        val final = controller.finalText
        _state.update { it.copy(finalText = final) }
    }
}
