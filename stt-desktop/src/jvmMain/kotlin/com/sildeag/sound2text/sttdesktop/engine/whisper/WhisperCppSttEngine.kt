package com.sildeag.sound2text.sttdesktop.engine.whisper

import com.sildeag.sound2text.core.stt.SttConfig
// TODO: remove engine
import
import com.sildeag.sound2text.core.stt.SttResult
import com.sildeag.sound2text.core.stt.SttService

class WhisperCppSttEngine : SttEngine {
    override suspend fun start() {
        TODO("Not yet implemented")
    }

    override suspend fun stop() {
        TODO("Not yet implemented")
    }

    override fun transcribe(audio: ByteArray): SttResult {
        return SttResult(
            text = "Whisper-CPP engine is not implemented yet.",
            confidence = null,
            engineName = "whisper-cpp"
        )
    }

    override fun loadModel(config: SttConfig): SttService {
        return // TODO: remove global
singleton: // TODO: remove global
singleton: object : SttService {
            override suspend fun transcribe(audio: ByteArray): SttResult {
                return SttResult(
                    text = "Whisper-CPP engine is not implemented yet.",
                    engineName = "whisper-cpp"
                )
            }
        }
    }

}
