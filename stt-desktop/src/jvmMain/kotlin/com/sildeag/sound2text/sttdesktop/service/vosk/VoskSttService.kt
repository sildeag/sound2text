package com.sildeag.sound2text.sttdesktop.service.vosk

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.sttdesktop.SttResult
import com.sildeag.sound2text.sttdesktop.service.SttService
import org.vosk.Model
import org.vosk.Recognizer
import java.nio.ByteBuffer
import java.nio.ByteOrder

class VoskSttService(
    private val model: Model,
    private val config: SttConfig
) : SttService {
    // Vosk expects 16kHz mono PCM 16-bit
    private val sampleRate = 16_000f
    override fun transcribe(audio: ByteArray): SttResult {
        return try {
            val pcm = toShortArray(audio)

            Recognizer(model, sampleRate).use { recognizer ->
                val buffer = ByteBuffer
                    .allocate(pcm.size * 2)
                    .order(ByteOrder.LITTLE_ENDIAN)
                pcm.forEach { buffer.putShort(it) }
                recognizer.acceptWaveForm(buffer.array(),
                    buffer.array().size)
                val resultJson = recognizer.finalResult
                // You can parse JSON here if you want just the text
                val text = extractTextFromResult(resultJson)
                SttResult.Success(text)
            }
        } catch (e: Exception) {
            SttResult.Failure("Vosk transcription failed", e)
        }
    }
    private fun toShortArray(bytes: ByteArray): ShortArray {
        val buffer =
            ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN)
        val shorts = ShortArray(bytes.size / 2)
        buffer.asShortBuffer().get(shorts)
        return shorts
    }
    private fun extractTextFromResult(json: String): String {
        // Minimal, non-strict extraction; you can replace with
        // kotlinx.serialization later
        val key = "\"text\""
        val index = json.indexOf(key)
        if (index == -1) return json
        val start = json.indexOf('"', index + key.length) + 1
        val end = json.indexOf('"', start)
        if (start == 0 || end == -1) return json
        return json.substring(start, end)
    }
}