package com.sildeag.sound2text.sttdesktop

sealed class SpeechError {
object InvalidAudio : SpeechError()
    data class EngineFailure(val reason: String) : SpeechError()
}
