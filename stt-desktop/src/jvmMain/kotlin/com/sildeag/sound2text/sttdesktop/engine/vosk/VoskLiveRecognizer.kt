package com.sildeag.sound2text.sttdesktop.engine.vosk

import com.sildeag.sound2text.core.config.AudioSettings
import com.sildeag.sound2text.core.logging.Logger
import org.vosk.Model
import javax.sound.sampled.AudioFormat

class VoskLiveRecognizer(
    private val model: Model,
    private val audio: AudioSettings,
    private val logger: Logger
) {
    fun start(onText: (String) -> Unit) {
        val format = AudioFormat(
            audio.sampleRate,
            audio.bitDepth,
            audio.channels,
            audio.signed,
            audio.bigEndian
        )
        val info = DataLine.Info(TargetDataLine::class.java, format)
        val line = AudioSystem.getLine(info) as TargetDataLine
        line.open(format)
        line.start()
        val recognizer = Recognizer(model, audio.sampleRate)
        val buffer = ByteArray(4096)
        while (true) {
            val bytesRead = line.read(buffer, 0, buffer.size)
            if (recognizer.acceptWaveForm(buffer, bytesRead)) {
                onText(recognizer.result)
            }
        }
    }
}