package com.sildeag.sound2text.core.stt.engine

import com.sildeag.sound2text.core.stt.model.SttResult

data class UnifiedBackendResult(
    val text: String,
    val isFinal: Boolean
)

interface UnifiedBackend {
    suspend fun start()
    suspend fun process(chunk: ByteArray): UnifiedBackendResult
    suspend fun finish(): SttResult
    suspend fun stop()

}
