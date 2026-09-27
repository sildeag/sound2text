package com.sildeag.sound2text.featurerecording.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sildeag.sound2text.core.audio.RecordingSource
import com.sildeag.sound2text.core.audio.toAmplitude
import com.sildeag.sound2text.core.stt.engine.SttEngine
import com.sildeag.sound2text.core.stt.model.SttResult
import com.sildeag.sound2text.core.stt.streaming.SttStreamingController
import com.sildeag.sound2text.featurerecording.recording.RecordingState
import com.sildeag.sound2text.featurerecording.storage.TranscriptStorage
import com.sildeag.sound2text.uicommon.stt.SttUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SttRecordingViewModel(
    private val engine: SttEngine,
    private val recordingSource: RecordingSource,
    private val storage: TranscriptStorage
) : ViewModel() {

    private val controller = SttStreamingController(engine) { result ->
        when (result) {
            is SttResult.Partial -> onPartial(result.text)
            is SttResult.Final -> onFinal(result.text)
        d
    val uiState: StateFlow<SttUiState> = _uiState

    private val _waveform = MutableStateFlow<List<Float>>(emptyList())
    val waveform: StateFlow<List<Float>> = _waveform

    fun startRecording() = viewModelScope.launch {
        _uiState.update { it.copy(recordingState = RecordingState.Starting) }

        controller.start()

        _uiState.update { it.copy(recordingState = RecordingState.Recording) }

        recordingSource.start { bytes ->
            val amp = bytes.toAmplitude()
            updateWaveform(amp)
            controller.feed(bytes)
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
