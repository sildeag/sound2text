package com.sildeag.sound2text.core.stt.engine
import com.sildeag.sound2text.core.stt.model.SttResult
import com.sildeag.sound2text.core.stt.model.UnifiedBackendResult

interface UnifiedBackend {
    suspend fun start()
    suspend fun process(chunk: ByteArray): SttResult
    suspend fun finish(): SttResult // Returns remaining buffer string
    suspend fun stop()
}