package com.sildeag.sound2text.sttandroid.engine.vosk

import com.sildeag.sound2text.core.stt.SttEngine
import com.sildeag.sound2text.core.stt.SttResult
import org.vosk.Model
import org.vosk.Recognizer

class VoskAndroidSttEngine(
    private val language: Model,
    private val modelPath: String?,
    private val modelFile: String?,
    private val androidModelDir: String?,
    private val androidModelFile: String?,
    private val sampleRate: Float
) : SttEngine {
    private var model: Model? = null
    private var recognizer: Recognizer? = null
    private var callback: ((String) -> Unit)? = null
    override fun start(onResult: (String) -> Unit) {
        callback = onResult
        // Android uses androidModelDir or androidModelFile
        val path = androidModelDir ?: androidModelFile
        ?: error("VoskAndroidSttEngine: No Android model path provided")
                model = Model(path)
                recognizer = Recognizer(model, sampleRate)
    }
    override fun processAudio(data: ByteArray) {
        val rec = recognizer ?: return
        val text = if (rec.acceptWaveForm(data)) {
            rec.result
        } else {
            rec.partialResult
        }
        callback?.invoke(text)
    }

    override suspend fun start() {
        TODO("Not yet implemented")
    }

    override fun stop() {
        recognizer?.close()
        model?.close()
        recognizer = null
        model = null
    }

    override suspend fun transcribe(chunk: ByteArray): String {
        TODO("Not yet implemented")
    }

    override suspend fun recognizeOnce(): SttResult? {
        TODO("Not yet implemented")
    }

    override fun finalResult(): String? {
        return recognizer?.finalResult
    }
}
