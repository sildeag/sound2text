package com.sildeag.sound2text.sttandroid.engine.vosk
import com.sildeag.sound2text.core.stt.SttEngine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.vosk.android.StorageService
import org.vosk.Model
import org.vosk.Recognizer
class VoskAndroidSttEngine(
    private val model: Model,
    private val sampleRate: Float = 16000f
) : SttEngine {
    private var recognizer: Recognizer? = null
    suspend fun start() : Unit = withContext(Dispatchers.IO) {
        recognizer = Recognizer(model, sampleRate)
    }
    override suspend fun stop() = withContext(Dispatchers.IO) {
        recognizer?.close()
        recognizer = null
    }

    suspend fun startStreaming(
        onPartial: (String) -> Unit,
        onFinal: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }
*/
    override suspend fun transcribe(bytes: ByteArray): String =
        withContext(Dispatchers.IO) {
            val rec = recognizer ?: return@withContext "Vosk engine not started"
            val accepted = rec.acceptWaveForm(bytes, bytes.size)
            if (accepted) rec.result else rec.partialResult
        }

    override suspend fun processAudio(chunk: ByteArray) {
        TODO("Not yet implemented")
    }

    override suspend fun recognizeOnce(): String? =
        withContext(Dispatchers.IO) {
            recognizer?.finalResult
        }
}
