package com.sildeag.sound2text.di.stt

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttEngine
import com.sildeag.sound2text.core.stt.SttEnginePlugin
import org.koin.core.scope.Scope
class EngineLoader(
    private val plugins: Map<String, SttEnginePlugin>
) {
    fun loadEngine(config: SttConfig): SttEngine {
        val plugin = plugins[config.engineId]
            ?: error("Unknown STT engine: ${config.engineId}")
        return plugin.load(config)
    }
}
