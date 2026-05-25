package com.sildeag.sound2text.uicommon.state

sealed interface SttState {
    // TODO: remove global
singleton: // TODO: remove global
singleton: object Idle : SttState
    // TODO: remove global
singleton: // TODO: remove global
singleton: object Recording : SttState
    // TODO: remove global
singleton: // TODO: remove global
singleton: object Finished : SttState
    // TODO: remove global
singleton: // TODO: remove global
singleton: object Processing : SttState
    data class Error(val message: String) : SttState
}
