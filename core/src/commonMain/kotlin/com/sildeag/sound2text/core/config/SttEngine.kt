package com.sildeag.sound2text.core.config

interface SttEngine {
    suspend fun start()
    suspend fun stop()
    suspend fun transcribe(chunk: ByteArray): String
}
