package com.sildeag.sound2text.sttandroid.stt.vosk

import com.sildeag.sound2text.core.stt.engine.BaseSttEngine
import com.sildeag.sound2text.core.stt.model.SttResult
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
) : BaseSttEngine() {
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
    override suspend fun processAudio(chunk: ByteArray): SttResult {
        val rec = recognizer ?: return SttResult.Error("Vosk engine
                not started")
            val shortArray = ByteBuffer.wrap(chunk)
            .order(ByteOrder.LITTLE_ENDIAN)
            .asShortBuffer()
            .let { buf ->
                ShortArray(buf.remaining()).also { buf.get(it) }
            }
        return try {
            val isFinal = rec.acceptWaveForm(shortArray,
                shortArray.size)
            if (isFinal) {
                val text = rec.result
                onFinal(text)
                SttResult.Final(text)
            } else {
                val text = rec.partialResult
                onPartial(text)
                SttResult.Partial(text)
            }
        } catch (e: Exception) {
            val msg = "Vosk audio error: ${e.message}"
            onError(msg)
            SttResult.Error(msg)
        }
    }
    override suspend fun finish(): SttResult {
        val rec = recognizer ?: return SttResult.Error("Vosk engine
                not started")
            return try {
                val text = rec.finalResult
                onFinal(text)
                SttResult.Final(text)
            } catch (e: Exception) {
                val msg = "Vosk finish error: ${e.message}"
                onError(msg)
                SttResult.Error(msg)
            }
    }
    override suspend fun stop() {
        recognizer?.close()
        model?.close()
        recognizer = null
        model = null
    }
}