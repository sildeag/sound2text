package com.sildeag.sound2text.sttandroid.audio

import android.Manifest
import com.sildeag.sound2text.core.stt.streaming.SttStreamingController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.media.AudioRecord
import android.media.AudioFormat
import android.media.MediaRecorder
import androidx.annotation.RequiresPermission

class AndroidAudioRecorder(
    private val streamingController: SttStreamingController,
    private val sampleRate: Int = 16000,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
) {
    private var audioRecord: AudioRecord? = null
    private var recordingThread: Thread? = null
    @Volatile private var isRecording = false

    @RequiresPermission(Manifest.permission.RECORD_AUDIO)
    suspend fun start() {
        val minBufferSize = AudioRecord.getMinBufferSize(
            sampleRate,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT
        )

        audioRecord = AudioRecord(
            MediaRecorder.AudioSource.MIC,
            sampleRate,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            minBufferSize
        )

        audioRecord?.startRecording()
        streamingController.start()
        isRecording = true

        recordingThread = Thread {
            val buffer = ByteArray(minBufferSize)
            while (isRecording) {
                val read = audioRecord?.read(buffer, 0, buffer.size) ?: 0
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

        audioRecord?.stop()
        audioRecord?.release()
        audioRecord = null

        streamingController.stop()
    }
}
