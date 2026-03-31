package com.sildeag.sound2text.sttdesktop.jvm

import com.sildeag.sound2text.sttdesktop.SttConfig
import com.sildeag.sound2text.sttdesktop.SttEngine
import com.sildeag.sound2text.sttdesktop.SttResult
import com.sildeag.sound2text.sttdesktop.SttService

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