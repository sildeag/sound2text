package com.sildeag.sound2text.core.stt

interface SttService {
    suspend fun start()
    suspend fun stop()
    suspend fun transcribe(chunk: ByteArray): SttResult
}

