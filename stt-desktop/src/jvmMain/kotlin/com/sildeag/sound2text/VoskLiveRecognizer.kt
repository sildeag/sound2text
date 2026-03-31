package com.sildeag.sound2text.vosk.engine.stt

import com.sildeag.sound2text.pdfdesktop.AudioSettings
import com.sildeag.sound2text.core.logging.Logger
import com.sildeag.sound2text.pdfdesktop.*


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
