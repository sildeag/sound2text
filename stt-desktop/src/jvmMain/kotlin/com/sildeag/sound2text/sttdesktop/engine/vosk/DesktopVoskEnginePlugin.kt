package com.sildeag.sound2text.sttdesktop.engine.vosk

import com.sildeag.sound2text.core.stt.SttEngine
import com.sildeag.sound2text.core.stt.SttEnginePlugin
import com.sildeag.sound2text.core.stt.SttModelInfo

class DesktopVoskEnginePlugin(
    private val engineFactory: (modelPath: String, language: String?) -> SttEngine
) : SttEnginePlugin {
    override val engineName: String = "vosk"
    override val displayName: String = "Vosk"
    override fun createEngine(model: SttModelInfo): SttEngine {
        val path = model.path ?: error("Vosk model requires a path")
        return engineFactory(path, model.language)
    }
}