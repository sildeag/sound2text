package com.sildeag.sound2text.sttdesktop.stt.unified

import com.sildeag.sound2text.core.stt.engine.EngineType
import com.sildeag.sound2text.core.stt.engine.UnifiedBackend
import com.sildeag.sound2text.core.stt.model.SttResult
import com.sildeag.sound2text.core.stt.model.UnifiedBackendResult

class UnifiedBackendRouter(
    private val voskBackend: UnifiedBackend,
    private val whisperBackend: UnifiedBackend
) : UnifiedBackend {

    var engineType: EngineType = EngineType.WHISPER

    private fun activeBackend(): UnifiedBackend = when (engineType) {
        EngineType.VOSK -> voskBackend
        EngineType.WHISPER -> whisperBackend
    }

    override suspend fun start() {
        activeBackend().start()
    }

    override suspend fun process(chunk: ByteArray): UnifiedBackendResult {
        return activeBackend().process(chunk)
    }

    override suspend fun finish(): SttResult {
        return activeBackend().finish()
    }

    override suspend fun stop() {
        activeBackend().stop()
    }
}