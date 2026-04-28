package com.sildeag.sound2text.core.stt

interface SttEngine {
    suspend fun start(): Unit
    suspend fun stop(): Unit
    suspend fun transcribe(chunk: ByteArray): String

    suspend fun recognizeOnce(): SttResult?
}
