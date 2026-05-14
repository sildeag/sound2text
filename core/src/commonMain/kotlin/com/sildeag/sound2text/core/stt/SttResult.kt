package com.sildeag.sound2text.core.stt

sealed class SttResult {
    val text: String

    data class Success(val data: SttTranscriptionData) : SttResult()
    data class Failure(val message: String, val cause: Throwable? = null) :
        SttResult()
}



