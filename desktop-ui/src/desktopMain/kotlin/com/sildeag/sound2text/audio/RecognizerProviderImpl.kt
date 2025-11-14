package com.sildeag.sound2text.audio

import com.sildeag.sound2text.core.logging.Logger
import org.vosk.Model
import org.vosk.Recognizer
import java.io.File
import javax.sound.sampled.AudioFormat

class RecognizerProviderImpl(
private val modelPath: String,
private val logger: Logger
) {
    fun create(): Recognizer? {
        val modelFile = File(modelPath)
        if (!modelFile.exists()) {
            logger.error("Model file not found at $modelPath")
            return null
        }
        val model = Model(modelPath)
        return Recognizer(model, 16000.0f)
    }
    fun getAudioFormat(): AudioFormat {
        return AudioFormat(16000.0f, 16, 1, true, false)
    }
}

/*
package com.sildeag.sound2text.audio

import org.vosk.Model
import org.vosk.Recognizer
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.TextArea
import javafx.scene.layout.VBox
import javafx.stage.Stage
import kotlin.concurrent.thread
import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.DataLine
import javax.sound.sampled.TargetDataLine


class SpeechApp : Application() {

    override fun start(primaryStage: Stage) {
        val textArea = TextArea()
        val root = VBox(textArea)
        val scene = Scene(root, 400.0, 300.0)

        primaryStage.title = "Speech to Text"
        primaryStage.scene = scene
        primaryStage.show()

        thread {
            val model = Model("model") // Path to Vosk model folder
            val recognizer = Recognizer(model, 16000.0f)

            val format = AudioFormat(16000.0f, 16, 1, true, false)
            val info = DataLine.Info(TargetDataLine::class.java, format)
            val line = AudioSystem.getLine(info) as TargetDataLine
            line.open(format)
            line.start()

            val buffer = ByteArray(4096)
            while (true) {
                val bytesRead = line.read(buffer, 0, buffer.size)
                if (recognizer.acceptWaveForm(buffer, bytesRead)) {
                    val result = recognizer.result
                    val text = Regex("\"text\"\\s*:\\s*\"(.*?)\"").find(result)?.groupValues?.get(1)
                    if (!text.isNullOrBlank()) {
                        javafx.application.Platform.runLater {
                            textArea.appendText("$text\n")
                        }
                    }
                }
            }
        }
    }
}

fun main() {
    Application.launch(SpeechApp::class.java)
}

 */