package com.sildeag.sound2text.core.stt.engine
import com.sildeag.sound2text.core.stt.model.UnifiedBackendResult

interface UnifiedBackend {
    suspend fun start()
    suspend fun process(chunk: ByteArray): UnifiedBackendResult
    suspend fun finish(): String // Returns remaining buffer text string
    suspend fun stop()
}
