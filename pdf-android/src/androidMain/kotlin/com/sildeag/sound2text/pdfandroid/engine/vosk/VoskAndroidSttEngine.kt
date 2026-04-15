package com.sildeag.sound2text.pdfandroid.engine.vosk

import android.content.Context
import com.sildeag.sound2text.stt.*
import org.vosk.Model

class VoskAndroidSttEngine(
    private val context: Context,
    private val model: Model
) : SttEngine {
    override fun loadModel(config: SttConfig): SttService {
        return VoskAndroidSttService(context, model, config)
    }
}