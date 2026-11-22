package com.sildeag.sound2text.core.stt.engine
interface SttEngine {
    suspend fun start()
    suspend fun processAudio(chunk: ByteArray) // Returns Unit, relies on callbacks
    suspend fun finish(): String
    suspend fun stop()
}
abstract class BaseSttEngine : SttEngine {
    override suspend fun start() {}
    override suspend fun stop() {}
    override suspend fun finish(): String {
        return ""}
}
