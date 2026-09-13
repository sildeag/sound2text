package com.sildeag.sound2text.sttdesktop.audio

import com.sildeag.sound2text.core.stt.streaming.SttStreamingController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.sound.sampled.*

class DesktopAudioRecorder(
    private val streamingController: SttStreamingController,
    private val sampleRate: Float = 16000f,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
) {
    private var line: TargetDataLine? = null
    private var recordingThread: Thread? = null
    @Volatile private var isRecording = false

    suspend fun start() {
        val format = AudioFormat(
            sampleRate,
            16,
            1,
            true,
            false
        )

        val info = DataLine.Info(TargetDataLine::class.java, format)
        val targetLine = AudioSystem.getLine(info) as TargetDataLine
        targetLine.open(format)
        targetLine.start()
        line = targetLine

        streamingController.start()
        isRecording = true

        recordingThread = Thread {
            val buffer = ByteArray(4096)
            while (isRecording) {
                val read = targetLine.read(buffer, 0, buffer.size)
                if (read > 0) {
                    val chunk = buffer.copyOf(read)

                    // FIX: feed() is suspend → launch coroutine
                    scope.launch {
                        streamingController.feed(chunk)
                    }
                }
            }
        }.apply { start() }
    }

    suspend fun stop() {
        isRecording = false
        recordingThread?.join()
        recordingThread = null

        line?.stop()
        line?.close()
        line = null

        streamingController.stop()
    }
}

