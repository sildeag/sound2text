package com.sildeag.sound2text.uicommon.sound

sealed interface SoundState {
    data object Idle : SoundState
    data object Recording : SoundState
    data object Finished : SoundState
    data class Error(val message: String) : SoundState
}

