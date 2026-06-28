package com.sildeag.sound2text.uicommon.state

sealed interface SttState {
    object Idle : SttState
    object Recording : SttState
    object Finished : SttState
    object Processing : SttState
    data class Error(val message: String) : SttState
}
