package com.sildeag.sound2text.core.stt

import kotlinx.coroutines.flow.Flow

interface SttEngine {
    suspend fun start()
    suspend fun stop(): Unit
    suspend fun transcribe(chunk: ByteArray): SttResult

    suspend fun recognizeOnce(): SttResult?
}
