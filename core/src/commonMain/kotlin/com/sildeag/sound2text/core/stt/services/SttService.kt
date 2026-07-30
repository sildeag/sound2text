package com.sildeag.sound2text.core.stt.services

import com.sildeag.sound2text.core.stt.SttResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SttService(
    private val engine: SttEngine
) {

    val partial: Any

    suspend fun start() = withContext(Dispatchers.IO) {
        engine.start()
    }

    suspend fun stop() = withContext(Dispatchers.IO) {
        engine.stop()
    }

    fun processAudio(bytes: ByteArray) {
        engine.processAudio(bytes)
    }

    suspend fun transcribe(bytes: ByteArray): SttResult =
        withContext(Dispatchers.IO) {
            engine.transcribe(bytes)
        }

    suspend fun recognizeOnce(): SttResult? =
        withContext(Dispatchers.IO) {
            engine.recognizeOnce()
        }
}