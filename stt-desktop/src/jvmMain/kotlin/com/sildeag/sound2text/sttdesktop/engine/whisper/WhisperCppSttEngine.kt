package com.sildeag.sound2text.sttdesktop.engine.whisper

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttResult

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
        return
    }
}
