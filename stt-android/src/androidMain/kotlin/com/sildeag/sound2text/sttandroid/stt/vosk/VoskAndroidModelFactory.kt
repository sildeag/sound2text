package com.sildeag.sound2text.sttandroid.stt.vosk

import android.content.Context
import org.vosk.android.SpeechModel
import java.io.File
object VoskAndroidModelFactory {
    fun load(context: Context, configPath: String): SpeechModel {
        val file = File(configPath)
        return SpeechModel(file.absolutePath)
    }
}