package com.sildeag.sound2text.uicommon.sound

sealed interface SoundState {
    data // TODO: remove global
singleton: // TODO: remove global
singleton: object Idle : SoundState
    data // TODO: remove global
singleton: // TODO: remove global
singleton: object Recording : SoundState
    data // TODO: remove global
singleton: // TODO: remove global
singleton: object Finished : SoundState
    data class Error(val message: String) : SoundState
}

