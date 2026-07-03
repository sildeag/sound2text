package com.sildeag.sound2text.featurerecording.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sildeag.sound2text.core.audio.RecordingSource
import com.sildeag.sound2text.core.audio.toAmplitude
import com.sildeag.sound2text.core.stt.SttStreamingController
import com.sildeag.sound2text.featurerecording.recording.RecordingState
import com.sildeag.sound2text.featurerecording.storage.TranscriptStorage
import com.sildeag.sound2text.featurerecording.stt.SttUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SttRecordingViewModel(
    private val controller: SttStreamingController,
    private val recordingSource: RecordingSource,
    private val storage: TranscriptStorage
) : ViewModel() {
    private val _uiState = MutableStateFlow(SttUiState())
    val uiState: StateFlow<SttUiState> = _uiState
    private val _waveform = MutableStateFlow<List<Float>>(emptyList())
    val waveform: StateFlow<List<Float>> = _waveform
    init {
        observeController()
    }
    private fun observeController() {
        viewModelScope.launch {
            controller.partial.collect { text ->
                onPartial(text)
            }
        }
        viewModelScope.launch {
            controller.final.collect { text ->
                onFinal(text)
            }
        }
        viewModelScope.launch {
            controller.errors.collect { msg ->
                onError(msg)
            }
        }
    }
    fun startRecording() = viewModelScope.launch {
        _uiState.update { it.copy(recordingState = RecordingState.Starting) }
        controller.startStreaming()
        _uiState.update { it.copy(recordingState = RecordingState.Recording) }
        recordingSource.start { bytes ->
            val amp = bytes.toAmplitude()
            updateWaveform(amp)
            controller.processAudio(bytes)
        }
    }
    fun stopRecording() = viewModelScope.launch {
        _uiState.update { it.copy(recordingState = RecordingState.Processing) }
        recordingSource.stop()
        controller.stop()
    }

        private fun onPartial(text: String) {
        _uiState.update { it.copy(partialText = text) }
    }

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

    private fun saveTranscript(text: String) {}

    private fun onFinal(text: String) {
        _uiState.update {
            it.copy(
                finalText = text,
                recordingState = RecordingState.Idle
            )
        }
    }
    private fun onError(message: String) {
        _uiState.update {
            it.copy(
                recordingState = RecordingState.Error(message),
                errorMessage = message
            )
        }
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
