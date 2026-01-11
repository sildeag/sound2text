package com.sildeag.sound2text.stt.android
import android.content.Context
import com.sildeag.sound2text.stt.*
import org.vosk.Model
import org.vosk.android.Recognizer
import org.vosk.android.SpeechService
class VoskAndroidSttEngine(
    private val context: Context,
    private val model: Model
) : SttEngine {
    override fun loadModel(config: SttConfig): SttService {
        return VoskAndroidSttService(context, model, config)
    }
}