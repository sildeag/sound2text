package com.sildeag.sound2text.sttandroid.stt.vosk

import com.sildeag.sound2text.core.stt.engine.SttEngine
import org.vosk.Model
import org.vosk.Recognizer
import java.nio.ByteBuffer
import java.nio.ByteOrder

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

        // Convert PCM ByteArray → ShortArray (required by Vosk Android)
        val shortBuffer = ByteBuffer.wrap(chunk)
            .order(ByteOrder.LITTLE_ENDIAN)
            .asShortBuffer()

        val shortArray = ShortArray(shortBuffer.remaining())
        shortBuffer.get(shortArray)

        try {
            val isFinal = rec.acceptWaveForm(shortArray, shortArray.size)
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
