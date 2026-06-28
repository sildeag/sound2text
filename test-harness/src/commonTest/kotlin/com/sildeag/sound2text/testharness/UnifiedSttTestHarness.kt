package com.sildeag.sound2text.testharness

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttEngine
import com.sildeag.sound2text.core.stt.UnifiedEngineRegistry

class UnifiedSttTestHarness(
    private val registry: UnifiedEngineRegistry
) {
    fun loadEngine(config: SttConfig): SttEngine {
        val plugin = registry.getPlugin(config.engineName)
            ?: error("Engine not found: ${config.engineName}")
        return plugin.createFactory().load(config)
    }
}
