package com.sildeag.sound2text.core.stt.engine

import com.sildeag.sound2text.core.stt.model.SttResult

interface SttEngine {
    suspend fun start()
    suspend fun stop()
    suspend fun processAudio(chunk: ByteArray)
}