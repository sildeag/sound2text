package com.sildeag.sound2text.sttdesktop.engine.vosk
import com.sildeag.sound2text.core.stt.*
import kotlinx.coroutines.flow.Flow
class VoskSttService(
    private val engine: SttEngine
) : SttService {
    override suspend fun start(): Flow<SttResult> = engine.start()
    override suspend fun stop() {
        TODO("Not yet implemented")
    }

    override suspend fun transcribe(chunk: ByteArray): SttResult {
        TODO("Not yet implemented")
    }
}