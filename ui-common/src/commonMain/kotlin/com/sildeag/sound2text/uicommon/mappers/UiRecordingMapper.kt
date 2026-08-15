package com.sildeag.sound2text.uicommon.mappers

import com.sildeag.sound2text.uicommon.models.RecordingState
import com.sildeag.sound2text.uicommon.models.UiRecording
/**
 * Maps core RecordingState into UiRecording for UI consumption.
 */
class RecordingUiMapper {
    fun map(uicommon: RecordingState): UiRecording =
        UiRecording(
            isRecording = uicommon.isRecording,
            amplitude = core.amplitude,
            waveform = core.waveform,
            durationMs = core.durationMs,
            filePath = core.filePath,
            lastError = core.lastError
        )
}