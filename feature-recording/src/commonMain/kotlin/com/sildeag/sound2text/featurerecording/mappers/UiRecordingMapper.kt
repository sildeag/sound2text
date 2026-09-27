package com.sildeag.sound2text.featurerecording.mappers

import com.sildeag.sound2text.uicommon.models.RecordingState
import com.sildeag.sound2text.uicommon.models.UiRecording
/**
 * Maps core RecordingState into UiRecording for UI consumption.
 */
class RecordingUiMapper {
    fun map(state: RecordingState): UiRecording =
        UiRecording(
            isRecording = state.isRecording,
            amplitude = state.amplitude,
            waveform = state.waveform,
            durationMs = state.durationMs,
            filePath = state.filePath,
            lastError = state.lastError
        )
}
