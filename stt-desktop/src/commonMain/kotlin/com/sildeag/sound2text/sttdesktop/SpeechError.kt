package com.sildeag.sound2text.sttdesktop

sealed class SpeechError {
    // TODO: remove global
singleton: // TODO: remove global
singleton: object ModelNotLoaded : SpeechError()
    // TODO: remove global
singleton: // TODO: remove global
singleton: object InvalidAudio : SpeechError()
    data class EngineFailure(val reason: String) : SpeechError()
}
