package com.sildeag.sound2text.featurerecording.recording

import com.sildeag.sound2text.core.stt.SttResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.max
class WaveformLogic(
    private val recordingController: RecordingController,
    private val externalScope: CoroutineScope,
    private val maxPoints: Int = 64
) {
    private val _state = MutableStateFlow(WaveformState())
    val state: StateFlow<WaveformState> = _state
    private var job: Job? = null
    fun start() {
        if (job != null) return
        job = externalScope.launch(Dispatchers.Default) {
            recordingController.state.collectLatest { recState ->
                when (recState) {
                    is RecordingState.Streaming -> {
                        val amp = computeAmplitude(recState.partial)
                        pushAmplitude(amp)
                    }
                    is RecordingState.Finished,  RecordingState.Idle -> {
                        _state.value = WaveformState(emptyList())
                    }
                    else -> Unit
                }
            }
        }
    }
    fun stop() {
        job?.cancel()
        job = null
        _state.value = WaveformState(emptyList())
    }
    private fun computeAmplitude(result:
                                 com.sildeag.sound2text.core.stt.SttResult): Float {
        // Placeholder: if you later attach raw chunk info to SttResult,
        // compute RMS/peak from that. For now, just emit a neutral value.
        return 0.5f
    }
    private fun pushAmplitude(value: Float) {
        val current = _state.value.amplitudes
        val updated = (current + value).takeLast(maxPoints)
        _state.value = WaveformState(updated)
    }
}
