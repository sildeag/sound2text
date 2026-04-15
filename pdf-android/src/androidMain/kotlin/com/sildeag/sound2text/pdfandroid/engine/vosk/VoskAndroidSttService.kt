package com.sildeag.sound2text.pdfandroid.engine.vosk

import android.content.Context
import com.sildeag.sound2text.stt.*
import org.vosk.Model
import org.vosk.android.Recognizer
import org.vosk.android.SpeechService

class VoskAndroidSttService(
    private val context: Context,
    private val model: Model,
    private val config: SttConfig
) : SttService {
    private val sampleRate = 16000f
    override fun transcribe(audio: ByteArray): SttResult {
        return try {
            val recognizer = Recognizer(model, sampleRate)
            val speechService = SpeechService(recognizer, sampleRate)
            // Feed the audio buffer
            speechService.recognize(audio, audio.size)
            val resultJson = recognizer.finalResult
            val text = extractText(resultJson)
            SttResult.Success(text)
        } catch (e: Exception) {
            SttResult.Failure("Android Vosk transcription failed", e)
        }
    }
    private fun extractText(json: String): String {
        val key = "\"text\""
        val index = json.indexOf(key)
        if (index == -1) return json
        val start = json.indexOf('"', index + key.length) + 1
        val end = json.indexOf('"', start)
        if (start == 0 || end == -1) return json
        return json.substring(start, end)
    }
}
