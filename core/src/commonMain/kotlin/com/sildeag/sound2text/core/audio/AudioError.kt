package com.sildeag.sound2text.core.audio

sealed interface AudioError {
    data object MicrophoneUnavailable : AudioError
    data object PermissionDenied : AudioError
    data class Unexpected(val message: String) : AudioError
}
