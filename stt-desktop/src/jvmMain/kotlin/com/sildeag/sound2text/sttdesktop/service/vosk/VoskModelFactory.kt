package com.sildeag.sound2text.sttdesktop.service.vosk

import com.sildeag.sound2text.core.stt.SttConfig
import org.vosk.Model
import java.io.File
object VoskModelFactory {
    fun loadModel(config: SttConfig): Model {
        requireNotNull(config.modelPath) { "Desktop Vosk requires modelPath" }
        val fullPath = if (config.modelFile != null)
            File(config.modelPath, config.modelFile).absolutePath
        else
            config.modelPath
        return Model(fullPath)
    }
}

