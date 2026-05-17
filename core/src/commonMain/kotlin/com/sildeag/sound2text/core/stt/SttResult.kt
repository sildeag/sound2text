package com.sildeag.sound2text.core.stt

sealed class SttResult {
    sealed class Success : SttResult() {
        data class Partial(val data: SttTranscriptionData) : Success()
        data class Final(val data: SttTranscriptionData) : Success()
    }
    data class Failure(val message: String, val cause: Throwable? = null) : SttResult()
}