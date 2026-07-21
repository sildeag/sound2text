package com.sildeag.sound2text.core.stt.engine

import com.sildeag.sound2text.core.stt.ModelDescriptor
import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttModelInfo

interface SttEnginePlugin {
    //val id: String // "vosk", "whisper", "unified"
    val engineName: String  // "vosk", "whisper", "unified"
    val displayName: String // "Vosk", "Whisper", "Unified"
    // e.g., model IDs or descriptors
    fun availableModels(): List<SttModelInfo>
    // Create a concrete engine instance for a given model
    fun createEngine(model: SttModelInfo): SttEngine
    fun createFactory(): SttEngineFactory  // Preferred
    fun discoverModels(basePath: String): List<ModelDescriptor> // Preferred
    fun load(config: SttConfig): SttEngine
}