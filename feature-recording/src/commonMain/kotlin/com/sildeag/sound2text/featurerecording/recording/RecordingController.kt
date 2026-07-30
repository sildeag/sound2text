package com.sildeag.sound2text.featurerecording.recording

import com.sildeag.sound2text.core.audio.RecordingSource
import com.sildeag.sound2text.core.stt.services.SttService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
class RecordingController(
    private val recordingSource: RecordingSource,
    private val sttService: SttService,
    private val scope: CoroutineScope
) {
    private val _state = MutableStateFlow<RecordingState>(RecordingState.Idle)
    val state: StateFlow<RecordingState> = _state
    private val _partial = MutableStateFlow("")
    val partial: StateFlow<String> = _partial
    private val _final = MutableStateFlow("")
    val final: StateFlow<String> = _final
    private val _errors = MutableStateFlow<String?>(null)
    val errors: StateFlow<String?> = _errors
    fun start(collect: Any.(Any?) -> Unit) {
        scope.launch {
            _state.value = RecordingState.Starting
            sttService.start()
            recordingSource.start { bytes ->
                sttService.processAudio(bytes)
            }
            sttService.partial.collect { text ->
                _partial.value = text
            }
            _state.value = RecordingState.Recording
        }
    }
    fun stop() {
        scope.launch {
            _state.value = RecordingState.Stopping
            recordingSource.stop()
            _state.value = RecordingState.Processing
            val finalText = sttService.recognizeOnce()
            _final.value = finalText
            sttService.stop()
            _state.value = RecordingState.Finished(null)
        }
    }
    fun error(message: String) {
        _errors.value = message
        _state.value = RecordingState.Error(message)
    }
}



/*
import com.sildeag.sound2text.core.audio.RecordingSource
import com.sildeag.sound2text.core.stt.SttEngine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecordingController(
    private val recordingSource: RecordingSource,
    private val sttEngine: SttEngine,
    private val externalScope: CoroutineScope
) {
    private var recordingJob: Job? = null
    private val _state =
        MutableStateFlow<RecordingState>(RecordingState.Idle)
    val state: StateFlow<RecordingState> = _state
    fun start() {
        if (_state.value is RecordingState.Recording) return
        _state.value = RecordingState.Starting
        recordingJob = externalScope.launch(Dispatchers.IO) {
            sttEngine.start()
            _state.value = RecordingState.Recording
            recordingSource.start { chunk ->
                externalScope.launch {
                    val result = sttEngine.transcribe(chunk)
                    _state.value = RecordingState.Streaming(result)
                }
            }
        }
    }


    fun stop() {
        if (_state.value !is RecordingState.Recording &&
            _state.value !is RecordingState.Streaming
        ) return
        _state.value = RecordingState.Stopping
        recordingJob?.cancel()
        recordingJob = null
        externalScope.launch(Dispatchers.IO) {
            recordingSource.stop()
            val final = sttEngine.recognizeOnce()
            sttEngine.stop()
            _state.value = RecordingState.Finished(final)
        }
    }
}

 */