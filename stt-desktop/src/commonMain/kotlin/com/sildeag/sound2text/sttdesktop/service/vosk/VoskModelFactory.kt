package com.sildeag.sound2text.sttdesktop.service.vosk

import com.sildeag.sound2text.core.stt.SttConfig
import org.vosk.Model

object VoskModelFactory {
    fun loadModel(config: SttConfig): Model {
        requireNotNull(config.modelPath) { "Desktop Vosk requires modelPath" }
            return Model(config.modelPath)
        }
    }
