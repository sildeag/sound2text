package com.sildeag.sound2text.featurerecording.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sildeag.sound2text.core.audio.RecordingSource
import com.sildeag.sound2text.core.audio.toAmplitude
import com.sildeag.sound2text.core.stt.SttController
import com.sildeag.sound2text.featurerecording.recording.RecordingState
import com.sildeag.sound2text.featurerecording.stt.SttUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
class SttRecordingViewModel(
    private val controller: SttController,
    private val recordingSource: RecordingSource
) : ViewModel() {
    private val _uiState = MutableStateFlow(SttUiState())
    val uiState: StateFlow<SttUiState> = _uiState
    private val _waveform =
        MutableStateFlow<List<Float>>(emptyList())
    val waveform: StateFlow<List<Float>> = _waveform
    init {
        observeController()
    }
    private fun observeController() {
        // Partial text
        viewModelScope.launch {
            controller.partial.collect { text ->
                _uiState.update { it.copy(partialText = text) }
            }
        }
        // Final text
        viewModelScope.launch {
            controller.final.collect { text ->
                _uiState.update {
                    it.copy(
                        finalText = text,
                        recordingState = RecordingState.Idle
                    )
                }
            }
        }
        // Errors
        viewModelScope.launch {
            controller.errors.collect { msg ->
                _uiState.update {
                    it.copy(
                        errorMessage = msg,
                        recordingState = RecordingState.Error(msg)
                    )
                }
            }
        }
    }
    fun startRecording() = viewModelScope.launch {
        _uiState.update { it.copy(recordingState =
            RecordingState.Starting) }
        controller.start()
        _uiState.update { it.copy(recordingState =
            RecordingState.Recording) }
        recordingSource.start { bytes ->
            val amp = bytes.toAmplitude()
            updateWaveform(amp)
            controller.processAudio(bytes)
        }
    }
    fun stopRecording() = viewModelScope.launch {
        _uiState.update { it.copy(recordingState =
            RecordingState.Processing) }
        recordingSource.stop()
        controller.stop()
    }
    private fun updateWaveform(amplitude: Float) {
        _waveform.update { old ->
            smooth((old + amplitude).takeLast(200))
        }
    }
    private fun smooth(list: List<Float>): List<Float> {
        if (list.size < 3) return list
        return list.mapIndexed { i, v ->
            when (i) {
                0 -> (v + list[i + 1]) / 2f
                list.lastIndex -> (v + list[i - 1]) / 2f
                else -> (list[i - 1] + v + list[i + 1]) / 3f
            }
        }
    }
}