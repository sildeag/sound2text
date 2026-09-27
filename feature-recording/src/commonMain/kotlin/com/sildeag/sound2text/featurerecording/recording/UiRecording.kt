package com.sildeag.sound2text.featurerecording.recording

/**
 * UI model representing an active or completed audio recording.
 * Used by RecordingViewModel and SoundState.
 */
data class UiRecording(
    val amplitude: Float = 0f,
    val waveform: List<Float> = emptyList(),
    val durationMs: Long = 0L,
    val filePath: String? = null,
    val lastError: String? = null,
    val isRecording: Boolean = false
)
