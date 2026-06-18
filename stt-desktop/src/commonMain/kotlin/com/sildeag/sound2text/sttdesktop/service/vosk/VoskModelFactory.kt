package com.sildeag.sound2text.sttdesktop.service.vosk

import org.vosk.Model
import java.nio.file.Path
object VoskModelFactory {
    fun loadModel(modelPath: Path): Model {
        return Model(modelPath.toString())
    }
}

