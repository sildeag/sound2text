package com.sildeag.sound2text.sttdesktop.engine.vosk

import com.sildeag.sound2text.core.stt.SttResult
import com.sildeag.sound2text.core.stt.SttService
import org.vosk.Model
import org.vosk.Recognizer
class VoskSttService(
    private val model: Model
) : SttService {
    private var recognizer: Recognizer? = null
    override suspend fun start() {
        recognizer = Recognizer(model, 16000f)
    }
    override suspend fun stop() {
        recognizer?.close()
        recognizer = null
    }
    override suspend fun transcribe(chunk: ByteArray): SttResult {
        val rec = recognizer ?: return SttResult(
            text = "Vosk engine not started",
            engineName = "vosk"
        )
        val accepted = rec.acceptWaveForm(chunk)
        val text = if (accepted) {
            rec.result
        } else {
            rec.partialResult
        }
        return SttResult(
            text = text,
            engineName = "vosk"
        )
    }
}
