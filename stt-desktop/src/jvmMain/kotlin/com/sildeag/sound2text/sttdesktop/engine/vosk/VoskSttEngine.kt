package com.sildeag.sound2text.sttdesktop.engine.vosk

import com.sildeag.sound2text.core.config.SttSettings
import com.sildeag.sound2text.core.engine.AudioSource
import com.sildeag.sound2text.core.engine.SpeechToTextEngine
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