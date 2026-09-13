package com.sildeag.sound2text.sttdesktop.stt.vosk

import com.sildeag.sound2text.core.stt.engine.SttEngine
import org.vosk.Model
import org.vosk.Recognizer

class VoskSttEngine(
    private val modelPath: String,
    private val sampleRate: Int = 16000,
    private val onPartial: (String) -> Unit,
    private val onFinal: (String) -> Unit,
    private val onError: (String) -> Unit
) : SttEngine {

    private var model: Model? = null
    private var recognizer: Recognizer? = null

    override suspend fun start() {
        try {
            model = Model(modelPath)
            recognizer = Recognizer(model, sampleRate.toFloat())
        } catch (e: Exception) {
            onError("Vosk start error: ${e.message}")
        }
    }

    override suspend fun processAudio(chunk: ByteArray) {
        val rec = recognizer ?: return

        try {
            val isFinal = rec.acceptWaveForm(chunk, chunk.size)
            if (isFinal) {
                onFinal(rec.result)
            } else {
                onPartial(rec.partialResult)
            }
        } catch (e: Exception) {
            onError("Vosk audio error: ${e.message}")
        }
    }

    override suspend fun stop() {
        recognizer?.close()
        model?.close()
        recognizer = null
        model = null
    }
}
