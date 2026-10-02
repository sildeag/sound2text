package com.sildeag.sound2text.core.stt.engine

import com.sildeag.sound2text.core.stt.model.SttResult
interface SttEngine {
    suspend fun processAudio(chunk: ByteArray): SttResult
}
abstract class BaseSttEngine : SttEngine {
    open suspend fun start() {}
    open suspend fun stop() {}
    open suspend fun finish(): SttResult = SttResult.Final("")
}
