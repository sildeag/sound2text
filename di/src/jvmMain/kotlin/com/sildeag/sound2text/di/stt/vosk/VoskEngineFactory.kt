package com.sildeag.sound2text.di.stt.vosk

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttEngineFactory
import com.sildeag.sound2text.sttdesktop.service.SttService
import com.sildeag.sound2text.sttdesktop.service.vosk.VoskSttService
import org.vosk.Model

class VoskEngineFactory : SttEngineFactory {
    override fun loadModel(config: SttConfig): SttService {
        val model = Model(config.modelPath)
        return VoskSttService(model)
    }

    override fun load(config: SttConfig): com.sildeag.sound2text.core.stt.SttService {
        TODO("Not yet implemented")
    }
}
