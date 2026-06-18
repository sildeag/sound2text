package com.sildeag.sound2text.sttdesktop.engine.vosk

import com.sildeag.sound2text.core.stt.SttConfig
import org.vosk.Model
class VoskEngineFactory : SttEngineFactory, SttEngineFactory {
    override fun loadModel(config: SttConfig): SttService {
        val model = Model(config.modelPath)
        return VoskSttService(model)
    }

    override fun load(config: SttConfig): SttService {
        TODO("Not yet implemented")
    }
}
