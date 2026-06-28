package com.sildeag.sound2text.sttdesktop.engine.vosk

import com.sildeag.sound2text.core.stt.SttEngine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.vosk.Model
import org.vosk.Recognizer

class VoskDesktopSttEngine(
    private val modelPath: String,
    private val sampleRate: Float = 16000f
) : SttEngine {
    private var model: Model? = null
    private var recognizer: Recognizer? = null
    override suspend fun start() = withContext(Dispatchers.IO) {
        model = Model(modelPath)
        recognizer = Recognizer(model, sampleRate)
    }

    override suspend fun start(
        onPartial: (String) -> Unit,
        onFinal: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun processAudio(bytes: ByteArray) {
        TODO("Not yet implemented")
    }

    override suspend fun stop() = withContext(Dispatchers.IO) {
        recognizer?.close()
        model?.close()
        recognizer = null
        model = null
    }
    override suspend fun transcribe(bytes: ByteArray): String =
        withContext(Dispatchers.IO) {
            val rec = recognizer ?: return@withContext "Vosk engine not started"
            val accepted = rec.acceptWaveForm(bytes, bytes.size)
            if (accepted) rec.result else rec.partialResult
        }
    override suspend fun recognizeOnce(): String? =
        withContext(Dispatchers.IO) {
            recognizer?.finalResult
        }
}