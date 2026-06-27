package com.sildeag.sound2text.core.stt

import kotlinx.coroutines.flow.Flow
interface SttService {
    suspend fun start(): Flow<SttResult>
    suspend fun stop()
    suspend fun transcribe(chunk: ByteArray): SttResult
}

