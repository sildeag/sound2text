package com.sildeag.sound2text.core.stt

class UnifiedEngineRegistry(
    private val plugins: List<SttEnginePlugin>
) {
    fun listEngines(): List<String> =
        plugins.map { it.engineName }
    fun listModels(basePath: String): List<ModelDescriptor> =
        plugins.flatMap { plugin ->
            plugin.discoverModels(basePath)
        }
    fun getPlugin(engineName: String): SttEnginePlugin? =
        plugins.firstOrNull { it.engineName == engineName }
}