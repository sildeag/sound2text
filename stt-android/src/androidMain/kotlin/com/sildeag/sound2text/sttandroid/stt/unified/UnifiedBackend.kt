package com.sildeag.sound2text.sttandroid.stt.unified

import com.sildeag.sound2text.core.stt.engine.*
import com.sildeag.sound2text.core.stt.model.SttResult
import com.sildeag.sound2text.sttandroid.stt.vosk.VoskBackend
import com.sildeag.sound2text.sttandroid.stt.whisper.WhisperBackend
import com.sildeag.sound2text.sttandroid.stt.unified.UnifiedBackend as ActiveBackend


class UnifiedBackend(
    private val voskBackend: VoskBackend,
    private val whisperBackend: WhisperBackend
) : UnifiedBackend {

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



    suspend fun transcribe(
        chunks: Iterable<ByteArray>
    ): SttTranscriptionData {
        start()

        return try {
            chunks.forEach { process(it) }

            SttTranscriptionData(
                text = finish()
            )
        } finally {
            stop()
        }
    }
    ``

    override suspend fun finish(): SttResult =
        activeBackend().finish()

    override suspend fun stop() =
        activeBackend().stop()


}