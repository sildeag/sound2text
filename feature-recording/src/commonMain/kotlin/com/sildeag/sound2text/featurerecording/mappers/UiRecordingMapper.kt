package com.sildeag.sound2text.featurerecording.mappers

import com.sildeag.sound2text.featurerecording.recording.RecordingState
import com.sildeag.sound2text.featurerecording.recording.UiRecording

class RecordingUiMapper {

    fun map(state: RecordingState): UiRecording =
        when (state) {
            RecordingState.Idle ->
                UiRecording(status = "Idle")

            RecordingState.Starting ->
                UiRecording(status = "Starting")

            RecordingState.Recording ->
                UiRecording(status = "Recording")

            RecordingState.Stopping ->
                UiRecording(status = "Stopping")

            RecordingState.Processing ->
                UiRecording(status = "Processing")

            is RecordingState.Error ->
                UiRecording(
                    status = "Error",
                    lastError = state.message
                )
        }
}
/**
 * Maps core RecordingState into UiRecording for UI consumption.
 */
/*
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
*/