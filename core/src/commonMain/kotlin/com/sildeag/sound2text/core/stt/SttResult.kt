package com.sildeag.sound2text.core.stt

sealed class SttResult(text: String, engineName: String) {
    data class Success(val data: SttTranscriptionData) : SttResult()
    data class Failure(val message: String, val cause: Throwable? =
        null) : SttResult()
}

/*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


@Serializable
data class SttResult(
    val text: String,
    val engineName: String
) {
    companion object {
        private val json = Json { ignoreUnknownKeys = true }
        fun fromJson(raw: String): SttResult {
            return try {
                json.decodeFromString(SttResult.serializer(), raw)
            } catch (_: Exception) {
                SttResult(text = raw, engineName = "vosk")
            }
        }
    }
}
*/

/*
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
*/