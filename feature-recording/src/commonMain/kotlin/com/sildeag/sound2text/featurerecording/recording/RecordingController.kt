package com.sildeag.sound2text.featurerecording.recording

import com.sildeag.sound2text.core.stt.SttEngine
import com.sildeag.sound2text.core.stt.SttResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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