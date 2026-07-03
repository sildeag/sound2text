package com.sildeag.sound2text.core.stt

interface SttEngine {
    suspend fun start()
    suspend fun stop()
    suspend fun startStreaming (
        onPartial: (String) -> Unit,
        onFinal: (String) -> Unit,
        onError: (String) -> Unit
    )
    suspend fun transcribe(bytes: ByteArray): SttResult
    fun processAudio(bytes: ByteArray)
    suspend fun recognizeOnce(): SttResult?
}
