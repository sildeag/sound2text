package com.sildeag.sound2text.core.stt.plugins

import com.sildeag.sound2text.core.stt.ModelDescriptor
import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttEngine
import com.sildeag.sound2text.core.stt.SttEngineFactory
import com.sildeag.sound2text.core.stt.SttEnginePlugin


class VoskEnginePlugin : SttEnginePlugin, SttEnginePlugin {
    fun load(config: SttConfig): SttEngine {
        return VoskSttEngine(
            language = config.language,
            modelPath = config.modelPath,
            modelFile = config.modelFile,
            androidModelDir = config.androidModelDir,
            androidModelFile = config.androidModelFile,
            sampleRate = config.sampleRate
        )
    }

    override val engineName: String
        get() = TODO("Not yet implemented")

    override fun createFactory(): SttEngineFactory {
        TODO("Not yet implemented")
    }

    override fun discoverModels(basePath: String): List<ModelDescriptor> {
        TODO("Not yet implemented")
    }
}
