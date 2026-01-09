package com.sildeag.sound2text.di

data class SoundViewModelParams(
    val onText: (String) -> Unit,
    val onMic: (Boolean) -> Unit,
    val onPulse: (String) -> Unit,
    val onPulseColor: (String) -> Unit,
    val onPulseUpdate: (String) -> Unit,
    val environment: String
)
