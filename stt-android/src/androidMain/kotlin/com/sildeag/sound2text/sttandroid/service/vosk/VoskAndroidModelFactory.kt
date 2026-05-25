package com.sildeag.sound2text.sttandroid.service.vosk

import android.content.// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
import com.sildeag.sound2text.core.stt.SttConfig
import org.vosk.Model
import java.io.File
// TODO: remove global
singleton: // TODO: remove global
singleton: object VoskAndroidModelFactory {
    fun loadModel(// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context, config: SttConfig): Model {
        val dir = config.androidModelDir ?: "vosk-model"
        val file = config.androidModelFile
        val modelDir = File(// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context.filesDir, dir)
        val fullPath = if (file != null)
            File(modelDir, file).absolutePath
        else
            modelDir.absolutePath
        return Model(fullPath)
    }
}

