package com.sildeag.sound2text.core.service.recognizer

import com.sildeag.sound2text.core.logging.Logger
import org.vosk.Model
import org.vosk.Recognizer as VoskCoreRecognizer
import java.io.File
import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.DataLine
import javax.sound.sampled.TargetDataLine
import kotlin.concurrent.thread

class VoskRecognizerImpl(
    private val modelPath: String,
    private val logger: Logger
) : Recognizer {

    private var isListening = false
    private var recognizerThread: Thread? = null

    override fun start(onTextRecognized: (String) -> Unit) {
        recognizerThread = thread {
            try {
                val modelFile = File(modelPath)
                if (!modelFile.exists()) {
                    logger.error("Model file not found at $modelPath")
                    return@thread
                }
                val model = Model(modelPath)
                val recognizer = VoskCoreRecognizer(model, 16000.0f)
                val format = AudioFormat(16000.0f, 16, 1, true, false)
                val info = DataLine.Info(TargetDataLine::class.java, format)
                val line = AudioSystem.getLine(info) as TargetDataLine

                line.open(format)
                line.start()
                isListening = true
                val buffer = ByteArray(4096)
                while (isListening) {
                    val bytesRead = line.read(buffer, 0, buffer.size)
                    if (recognizer.acceptWaveForm(buffer, bytesRead)) {
                        val result = recognizer.result
                        val text = Regex("\"text\"\\s*:\\s*\"(.*?)\"")
                            .find(result)?.groupValues?.get(1)
                        if (!text.isNullOrBlank()) {
                            onTextRecognized(text)
                            logger.log("Recognized: $text")
                        }
                    }
                }
                line.stop()
                line.close()
            } catch (e: Exception) {
                logger.error("Error in recognizer thread", e)
            }
        }
    }

    override fun stop() {
        isListening = false
        recognizerThread?.join()
    }
}
