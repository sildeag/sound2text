package com.sildeag.sound2text.sttandroid.service.vosk

import android.content.Context
import com.sildeag.sound2text.core.stt.SttConfig
import org.vosk.Model
import java.io.File
object VoskAndroidModelFactory {
    fun loadModel(context: Context, config: SttConfig): Model {
        val dir = config.androidModelDir ?: "vosk-model"
        val file = config.androidModelFile
        val modelDir = File(context.filesDir, dir)
        val fullPath = if (file != null)
            File(modelDir, file).absolutePath
        else
            modelDir.absolutePath
        return Model(fullPath)
    }
}

