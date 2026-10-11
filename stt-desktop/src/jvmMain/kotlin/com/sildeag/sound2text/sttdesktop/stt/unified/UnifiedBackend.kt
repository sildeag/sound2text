package com.sildeag.sound2text.sttdesktop.stt.unified

import com.sildeag.sound2text.core.stt.engine.*
import com.sildeag.sound2text.core.stt.engine.UnifiedBackend
import com.sildeag.sound2text.core.stt.model.SttResult

class UnifiedBackend(
    private val voskBackend: com.sildeag.sound2text.core.stt.engine.UnifiedBackend,
    private val whisperBackend: com.sildeag.sound2text.core.stt.engine.UnifiedBackend
) : com.sildeag.sound2text.core.stt.engine.UnifiedBackend {

    var engineType: EngineType = EngineType.WHISPER

    private fun activeBackend(): UnifiedBackend =
        when (engineType) {
            EngineType.VOSK -> voskBackend
            EngineType.WHISPER -> whisperBackend
        }

    override suspend fun start() =
        activeBackend().start()

    override suspend fun process(
        chunk: ByteArray
    ): UnifiedBackendResult =
        activeBackend().process(chunk)

    override suspend fun finish(): SttResult =
        activeBackend().finish()

    override suspend fun stop() =
        activeBackend().stop()


}