package com.sildeag.sound2text.uicommon.stt.vosk

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttEngine
import com.sildeag.sound2text.core.stt.SttEnginePlugin

class VoskEnginePlugin : SttEnginePlugin {
    override fun load(config: SttConfig): SttEngine {
        return VoskSttEngine(
            language = config.language,
            modelPath = config.modelPath,
            modelFile = config.modelFile,
            androidModelDir = config.androidModelDir,
            androidModelFile = config.androidModelFile,
            sampleRate = config.sampleRate
        )
    }
}