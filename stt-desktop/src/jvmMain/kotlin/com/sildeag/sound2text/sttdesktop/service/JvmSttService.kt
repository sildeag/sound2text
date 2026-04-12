package com.sildeag.sound2text.sttdesktop.service

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttEngine
import com.sildeag.sound2text.core.stt.SttService
import com.sildeag.sound2text.sttdesktop.SttResult

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