package com.sildeag.sound2text.sttdesktop.engine.whisper

import com.sildeag.sound2text.core.stt.SttEngine
import com.sildeag.sound2text.core.stt.SttEnginePlugin
import com.sildeag.sound2text.core.stt.SttModelInfo

class DesktopWhisperEnginePlugin(
    private val engineFactory: (modelPath: String, language: String?) -> SttEngine
) : SttEnginePlugin {
    override val engineName: String = "whisper"
    override val displayName: String = "Whisper"
    override fun createEngine(model: SttModelInfo): SttEngine {
        val path = model.path ?: error("Whisper model requires a path")
        return engineFactory(path, model.language)
    }
}