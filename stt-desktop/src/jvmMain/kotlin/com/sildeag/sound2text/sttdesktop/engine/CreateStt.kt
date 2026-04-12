package com.sildeag.sound2text.sttdesktop.engine

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.sttdesktop.service.JvmSttService

class CreateStt(
    private val registry: UnifiedEngineRegistry
) {
    fun create(config: SttConfig): SttService {
        val plugin = registry.getPlugin(config.engineName)
            ?: error("No STT plugin registered for engine: $
            {config.engineName}")
                val factory = plugin.createFactory()
        val engine = factory.load(config)
        return JvmSttService(engine)
    }
}
