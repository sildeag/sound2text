package com.sildeag.sound2text.core.stt.engine

data class UnifiedBackendResult(
    val text: String,
    val isFinal: Boolean
)

interface UnifiedBackend {
    suspend fun start()
    suspend fun process(chunk: ByteArray): UnifiedBackendResult
    suspend fun finish(): String
    suspend fun stop()
}
