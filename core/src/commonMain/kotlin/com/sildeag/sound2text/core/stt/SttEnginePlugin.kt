package com.sildeag.sound2text.core.stt

interface SttEnginePlugin {
    val engineName: String
    fun createFactory(): SttEngineFactory
    fun discoverModels(basePath: String): List<ModelDescriptor>
}
