package com.sildeag.sound2text.core.stt.engine

import com.sildeag.sound2text.core.stt.model.ModelDescriptor
import com.sildeag.sound2text.core.stt.model.SttModelInfo
class UnifiedEnginePlugin(
    private val plugins: List<SttEnginePlugin>
) : SttEnginePlugin {
    override val engineName = "unified"
    override val displayName = "Unified"
    override fun availableModels(): List<SttModelInfo> =
        plugins.flatMap { it.availableModels() }
    override fun discoverModels(basePath: String):
            List<ModelDescriptor> =
        plugins.flatMap { it.discoverModels(basePath) }
    override fun createEngine(model: SttModelInfo): SttEngine {
        val engines = plugins.map { it.createEngine(model) }
        return UnifiedSttEngine(engines)
    }
}
