package com.sildeag.sound2text.core.stt.engine

import com.sildeag.sound2text.core.stt.model.SttResult

interface SttEngine {

    suspend fun start()

    suspend fun processAudio(
        chunk: ByteArray
    ): SttResult

    suspend fun finish(): SttResult

    suspend fun stop()
}

abstract class BaseSttEngine : SttEngine {

    override suspend fun start() {}

    override suspend fun stop() {}

    override suspend fun finish(): SttResult =
        SttResult.Final("")
}