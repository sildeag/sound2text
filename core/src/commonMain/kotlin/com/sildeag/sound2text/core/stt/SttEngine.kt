package com.sildeag.sound2text.core.stt

import kotlinx.coroutines.flow.Flow

interface SttEngine {
    suspend fun start(
        onPartial: (String) -> Unit,
        onFinal: (String) -> Unit,
        onError: (String) -> Unit
    )
    suspend fun processAudio(bytes: ByteArray)
    suspend fun stop()
}

/*
interface SttEngine {
    val engineId: String
    val modelId: String
    suspend fun start()
    suspend fun stop(): Unit
    suspend fun transcribe(chunk: ByteArray): SttResult
    suspend fun processAudio(chunk: ByteArray)

    suspend fun recognizeOnce(): SttResult?
}
*/
//interface SttEngine {
//    val engineId: String
//    val modelId: String
//    suspend fun start()
//    suspend fun stop()
//    suspend fun processAudio(chunk: ByteArray)
//}