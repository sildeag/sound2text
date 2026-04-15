package com.sildeag.sound2text.pdfandroid.service

class AndroidSttService(
    private val engine: SttEngine
) : SttService {
    override fun transcribe(audio: ByteArray): SttResult {
        return try {
            engine.transcribe(audio)
        } catch (e: Exception) {
            SttResult.Failure("Android STT failed", e)
        }
    }
}

