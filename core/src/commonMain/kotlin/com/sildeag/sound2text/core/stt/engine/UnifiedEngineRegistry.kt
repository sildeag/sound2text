package com.sildeag.sound2text.core.stt.engine

import com.sildeag.sound2text.core.stt.model.SttModelInfo

class UnifiedEngineRegistry(
    private val plugins: List<SttEnginePlugin>
) {
    fun listEngines(): List<String> =
        plugins.map { it.engineName }
    fun getPlugin(engineName: String): SttEnginePlugin? =
        plugins.firstOrNull { it.engineName == engineName }
    fun createEngine(model: SttModelInfo): SttEngine {
        val plugin = getPlugin(model.engineName)
            ?: error("No STT engine found for: ${model.engineName}")
        return plugin.createEngine(model)
    }
}