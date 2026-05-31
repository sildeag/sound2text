package com.sildeag.sound2text.core.stt

sealed class SttResult {
    sealed class Success(sttTranscriptionData: SttTranscriptionData) : SttResult() {
        data class Partial(val data: SttTranscriptionData) : Success(
            SttTranscriptionData(
                text = text,
                confidence = null,
                engineName = "vosk-android"
            )
        )
        data class Final(val data: SttTranscriptionData) : Success(
            SttTranscriptionData(
                text = text,
                confidence = null,
                engineName = "vosk-android"
            )
        )
    }
    data class Failure(val message: String, val cause: Throwable? = null) : SttResult()
}
