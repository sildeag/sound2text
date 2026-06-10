package com.sildeag.sound2text.sttdesktop.engine.vosk

import com.sildeag.sound2text.core.stt.SttEngine
import com.sildeag.sound2text.core.stt.SttResult
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
    override suspend fun stop() = withContext(Dispatchers.IO) {
        recognizer?.close()
        model?.close()
        recognizer = null
        model = null
    }
    override suspend fun transcribe(chunk: ByteArray): SttResult =
        withContext(Dispatchers.IO) {
            val rec = recognizer ?: return@withContext SttResult(
                text = "Vosk engine not started",
                engineName = "vosk"
            )
            val accepted = rec.acceptWaveForm(chunk,chunk.size)
            val text = if (accepted) rec.result else
                rec.partialResult
            SttResult(text = text, engineName = "vosk")
        }
    override suspend fun recognizeOnce(): SttResult? =
        withContext(Dispatchers.IO) {
            val rec = recognizer ?: return@withContext null
            SttResult(text = rec.finalResult, engineName = "vosk")
        }
}



/*
import com.sildeag.sound2text.core.stt.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.vosk.Model
import org.vosk.Recognizer
import javax.sound.sampled.*

class VoskSttEngine(
    private val model: Model,
    private val config: SttConfig
) : SttEngine {
    override suspend fun start(): Flow<SttResult> = callbackFlow {
        val recognizer = Recognizer(model, config.sampleRate)
        val format = AudioFormat(
            config.sampleRate,
            16,
            1,
            true,
            false
        )
        val line = AudioSystem.getTargetDataLine(format)
        line.open(format)
        line.start()
        val buffer = ByteArray(4096)
        try {
            while (true) {
                val read = line.read(buffer, 0, buffer.size)
                if (read > 0) {
                    val accepted = recognizer.acceptWaveForm(buffer,
                        read)
                    val json = if (accepted) recognizer.result else
                        recognizer.partialResult
                    trySend(SttResult.fromJson(json))
                }
            }
        } finally {
            line.stop()
            line.close()
            recognizer.close()
        }
    }

    override suspend fun stop() {
        TODO("Not yet implemented")
    }

    override suspend fun transcribe(chunk: ByteArray): SttResult {
        TODO("Not yet implemented")
    }

    override suspend fun recognizeOnce(): SttResult? {
        TODO("Not yet implemented")
    }



}
*/

/*
import com.sildeag.sound2text.core.config.SttSettings
// TODO: remove engine
import.AudioSource
// TODO: remove engine
import
import com.sildeag.sound2text.pdfdesktop.model.SttSettings
import com.sildeag.sound2text.vosk.diagnostics.VoskDiagnostics
import org.vosk.Model
import org.vosk.Recognizer
import java.nio.file.Files
import java.nio.file.Paths

class VoskSttEngine(
    private val settings: SttSettings,
    private val audioSource: AudioSource
) : SpeechToTextEngine {
    private var model: Model? = null
    private var recognizer: Recognizer? = null
    override fun startListening(onResult: (String) -> Unit) {
        model = VoskDiagnostics.loadModel(settings.modelPath)
        recognizer = Recognizer(model, settings.sampleRate)
        audioSource.start { chunk ->
            val rec = recognizer ?: return@start
            if (rec.acceptWaveForm(chunk)) onResult(rec.finalResult)
            else onResult(rec.partialResult)
        }
    }
    override fun stopListening() {
        audioSource.stop()
        recognizer?.close()
        model?.close()
    }
    override fun transcribe(path: String): String {
        val model = VoskDiagnostics.loadModel(settings.modelPath)
        val bytes = Files.readAllBytes(Paths.get(path))
        return VoskDiagnostics.transcribe(model, bytes,
            settings.sampleRate)
    }
    override fun transcribe(audioData: ByteArray): String {
        val model = VoskDiagnostics.loadModel(settings.modelPath)
        return VoskDiagnostics.transcribe(model, audioData,
            settings.sampleRate)
    }
}
*/