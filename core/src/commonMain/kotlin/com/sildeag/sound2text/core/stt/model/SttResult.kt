package com.sildeag.sound2text.core.stt.model

sealed class SttResult {
    data class Final(val text: String) : SttResult()
    data class Partial(val text: String) : SttResult()
    data class Failure(val reason: String) : SttResult()
}
