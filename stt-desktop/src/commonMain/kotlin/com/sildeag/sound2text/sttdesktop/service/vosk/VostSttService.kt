package com.sildeag.sound2text.sttdesktop.service.vosk

import com.sildeag.sound2text.core.stt.SttTranscriptionData
import com.sildeag.sound2text.core.stt.SttConfig
import kotlinx.coroutines.flow.Flow
import org.vosk.Model
import org.vosk.Recognizer
import java.nio.ByteBuffer
import java.nio.ByteOrder
class VoskSttService(
    private val model: Model,
    private val config: SttConfig
) : SttService {
    private val sampleRate = config.sampleRate
    override suspend fun start(): Flow<SttResult> {
        TODO("Not yet implemented")
    }

    override suspend fun stop() {
        TODO("Not yet implemented")
    }

    override suspend fun transcribe(audio: ByteArray): SttResult {
        return try {
            val pcm = toShortArray(audio)
            Recognizer(model, sampleRate).use { recognizer ->
                val buffer = ByteBuffer
                    .allocate(pcm.size * 2)
                    .order(ByteOrder.LITTLE_ENDIAN)
                pcm.forEach { buffer.putShort(it) }
                recognizer.acceptWaveForm(buffer.array(), buffer.array().size)
                val json = recognizer.finalResult
                val text = extractText(json)
                SttResult.Success(
                    SttTranscriptionData(
                        text = text,
                        confidence = null,
                        engineName = "vosk-android",
                        isFinal = TODO(),
                        timestamp = TODO()
                    )
                )
            }
        } catch (e: Exception) {
            SttResult.Failure("Vosk Android transcription failed", e)
        }
    }
    private fun toShortArray(bytes: ByteArray): ShortArray {
        val buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN)
        val shorts = ShortArray(bytes.size / 2)
        buffer.asShortBuffer().get(shorts)
        return shorts
    }
    private fun extractText(json: String): String {
        val key = "\"text\""
        val index = json.indexOf(key)
        if (index == -1) return json
        val start = json.indexOf('"', index + key.length) + 1
        val end = json.indexOf('"', start)
        if (start <= 0 || end <= start) return json
        return json.substring(start, end)
    }
}
