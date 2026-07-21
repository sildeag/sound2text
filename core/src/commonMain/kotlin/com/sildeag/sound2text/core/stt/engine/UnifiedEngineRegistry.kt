package com.sildeag.sound2text.core.stt.engine

import com.sildeag.sound2text.core.stt.SttConfig

class UnifiedEngineRegistry(
    private val plugins: List<SttEnginePlugin>
) {

    fun listEngines(): List<String> =
        plugins.map { it.engineName }

    fun getPlugin(engineName: String): SttEnginePlugin? =
        plugins.firstOrNull { it.engineName == engineName }

    fun loadEngine(config: SttConfig): SttEngine {
        val plugin = getPlugin(config.engineName)
            ?: error("No STT engine found for: ${config.engineName}")

        val factory = plugin.createFactory()
        return factory.load(config)
    }
}