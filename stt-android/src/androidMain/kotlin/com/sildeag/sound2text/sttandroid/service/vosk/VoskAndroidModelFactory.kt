package com.sildeag.sound2text.sttandroid.service.vosk

import android.content.Context
import org.vosk.Model
import java.io.File
object VoskAndroidModelFactory {
    fun loadModel(context: Context, dirName: String = "vosk-model"): Model {
        // Assumes model already unpacked into filesDir/dirName
        val modelDir = File(context.filesDir, dirName)
        return Model(modelDir.absolutePath)
    }
}
