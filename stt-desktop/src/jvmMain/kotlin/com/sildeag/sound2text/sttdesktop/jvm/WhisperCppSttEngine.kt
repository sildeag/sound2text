package com.sildeag.sound2text.sttdesktop.jvm

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttEngine
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
        return object : SttService {
            override fun transcribe(audio: ByteArray): SttResult {
                return SttResult(
                    text = "Whisper-CPP engine is not implemented yet.",
                    engineName = "whisper-cpp"
                )
            }
        }
    }

}
