package com.sildeag.sound2text.featurerecording.recording

import com.sildeag.sound2text.core.audio.RecordingSource
import javax.sound.sampled.*
class DesktopRecordingSource(
    private val sampleRate: Float = 16000f
) : RecordingSource {
    private var line: TargetDataLine? = null
    private var running = false
    override fun start(onChunk: (ByteArray) -> Unit) {
        val format = AudioFormat(
            sampleRate,
            16,
            1,
            true,
            false
        )
        val info = DataLine.Info(TargetDataLine::class.java, format)
        val mic = AudioSystem.getLine(info) as TargetDataLine
        mic.open(format)
        mic.start()
        line = mic
        running = true
        Thread {
            val buffer = ByteArray(4096)
            while (running) {
                val read = mic.read(buffer, 0, buffer.size)
                if (read > 0) {
                    onChunk(buffer.copyOf(read))
                }
            }
        }.start()
    }
    override fun stop() {
        running = false
        line?.stop()
        line?.close()
        line = null
    }
}
