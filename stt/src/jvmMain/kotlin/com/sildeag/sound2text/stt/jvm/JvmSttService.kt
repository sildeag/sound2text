package com.sildeag.sound2text.stt.jvm

import com.sildeag.sound2text.stt.SttConfig
import com.sildeag.sound2text.stt.SttEngine
import com.sildeag.sound2text.stt.SttResult
import com.sildeag.sound2text.stt.SttService

class JvmSttService(
    private val engine: SttEngine,
    private val config: SttConfig
) : SttService {
    override fun transcribe(audio: ByteArray): SttResult {
        return try {
            val stt = engine.loadModel(config)
            stt.transcribe(audio)
        } catch (e: Exception) {
            SttResult.Failure("Desktop STT failed", e)
        }
    }
}