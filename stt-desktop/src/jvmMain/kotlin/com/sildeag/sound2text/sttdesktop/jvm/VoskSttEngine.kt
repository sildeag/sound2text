package com.sildeag.sound2text.sttdesktop.jvm

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttEngine
import com.sildeag.sound2text.sttdesktop.*

class VoskSttEngine(
    private val defaultConfig: SttConfig = SttConfig(
        modelPath = "vosk-model-small-en-us",
        language = TODO(),
        modelFile = TODO()
    )
) : SttEngine {
    override fun loadModel(config: SttConfig): SttService {
        val effectiveConfig = config.copy(
            modelPath = config.modelPath ?: defaultConfig.modelPath
        )
        val modelName = effectiveConfig.modelPath
            ?: throw IllegalStateException("Vosk modelPath must not be null")
                val model = VoskModelLoader.load(modelName)
        return VoskSttService(
            model = model,
            config = effectiveConfig
        )
    }

    class VoskModelLoader {

    }
}
