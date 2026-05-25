package com.sildeag.sound2text.uidesktop.audio

// TODO: remove engine
import.AudioSource
import javax.sound.sampled.*
class DesktopAudioCapture : AudioSource {
    private var line: TargetDataLine? = null
    private var running = false
    override fun start(onChunk: (ByteArray) -> Unit) {
        val format = AudioFormat(16000f, 16, 1, true, false)
        val info = DataLine.Info(TargetDataLine::class.java, format)
        line = AudioSystem.getLine(info) as TargetDataLine
        line!!.open(format)
        line!!.start()
        running = true
        Thread {
            val buffer = ByteArray(4096)
            while (running) {
                val read = line!!.read(buffer, 0, buffer.size)
                if (read > 0) onChunk(buffer.copyOf(read))
            }
        }.start()
    }
    override fun stop() {
        running = false
        line?.stop()
        line?.close()
    }
}
