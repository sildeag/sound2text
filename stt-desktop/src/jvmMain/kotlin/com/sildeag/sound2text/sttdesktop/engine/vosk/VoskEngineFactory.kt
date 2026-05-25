package com.sildeag.sound2text.sttdesktop.engine.vosk

import com.sildeag.sound2text.core.stt.SttConfig
// TODO: remove engine
importFactory
import com.sildeag.sound2text.core.stt.SttService
import com.sildeag.sound2text.sttdesktop.service.VoskSttService
import com.sildeag.sound2text.sttdesktop.service.vosk.VoskSttService
import org.vosk.Model
class VoskEngineFactory : SttEngineFactory {
    override fun loadModel(config: SttConfig): SttService {
        val model = Model(config.modelPath)
        return VoskSttService(model)
    }
}
