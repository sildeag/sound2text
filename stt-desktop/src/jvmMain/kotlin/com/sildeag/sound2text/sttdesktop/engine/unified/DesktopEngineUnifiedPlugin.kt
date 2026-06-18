package com.sildeag.sound2text.sttdesktop.engine.unified

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttEngine
import com.sildeag.sound2text.core.stt.SttEnginePlugin
import com.sildeag.sound2text.core.stt.SttModelInfo
import com.sildeag.sound2text.core.stt.SttResult
import com.sildeag.sound2text.di.stt.EngineLoader

class DesktopUnifiedEnginePlugin(
    private val engineLoader: EngineLoader
) : SttEnginePlugin {
    override val engineName: String = "unified"
    override val displayName: String = "Unified"
    override fun createEngine(model: SttModelInfo): SttEngine {
        val primaryConfig = SttConfig(
            engineName = "vosk",
            modelInfo = model,
            language = model.language,
            extra = model.path?.let { mapOf("modelPath" to it) } ?: emptyMap()
        )
        val fallbackConfig = SttConfig(
            engineName = "whisper",
            modelInfo = model,
            language = model.language,
            extra = model.path?.let { mapOf("modelPath" to it) } ?: emptyMap()
        )
        val primary = engineLoader.loadEngine(primaryConfig)
        val fallback = engineLoader.loadEngine(fallbackConfig)
        return UnifiedEngine(primary, fallback)
    }
}
class UnifiedEngine(
    private val primary: SttEngine,
    private val fallback: SttEngine?
) : SttEngine {
    override suspend fun start() {
        primary.start()
        fallback?.start()
    }
    override suspend fun processAudio(chunk: ByteArray): SttResult? {
        return primary.processAudio(chunk) ?: fallback?.processAudio(chunk)
    }
    override suspend fun stop(): SttResult? {
        return primary.stop() ?: fallback?.stop()
    }
    override suspend fun recognizeOnce(): SttResult? {
        return primary.recognizeOnce() ?: fallback?.recognizeOnce()
    }
}
