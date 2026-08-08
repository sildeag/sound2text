package com.sildeag.sound2text.uicommon.state

data class SoundState(
    val isRecording: Boolean = false,
    val amplitude: Float = 0f,
    val waveform: List<Float> = emptyList(),
    val durationMs: Long = 0L,
    val lastError: String? = null
)
