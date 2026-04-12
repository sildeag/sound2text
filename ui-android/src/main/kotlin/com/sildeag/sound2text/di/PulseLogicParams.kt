package com.sildeag.sound2text.di

import com.sildeag.sound2text.service.recognizer.Recognizer

data class PulseLogicParams(
    val onText: (String) -> Unit,
    val onMic: (Boolean) -> Unit,
    val onPulse: (String) -> Unit,
    val onPulseUpdate: (String) -> Unit,
    val onPulseColor: (String) -> Unit,
    val recognizerFactory: () -> Recognizer,
    val environment: String
)