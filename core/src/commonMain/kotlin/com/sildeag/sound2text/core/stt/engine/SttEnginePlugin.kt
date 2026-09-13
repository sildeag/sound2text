package com.sildeag.sound2text.core.stt.engine
import com.sildeag.sound2text.core.stt.model.ModelDescriptor
import com.sildeag.sound2text.core.stt.model.SttModelInfo
interface SttEnginePlugin {
    val engineName: String
    val displayName: String
    fun availableModels(): List<SttModelInfo>
    fun discoverModels(basePath: String): List<ModelDescriptor>
    fun createEngine(model: SttModelInfo): SttEngine
}

/*
package com.sildeag.sound2text.core.stt.engine
import com.sildeag.sound2text.core.stt.model.ModelDescriptor
import com.sildeag.sound2text.core.stt.model.SttModelInfo
interface SttEnginePlugin {
    val engineName: String
    val displayName: String
    fun availableModels(): List<SttModelInfo>
    fun discoverModels(basePath: String): List<ModelDescriptor>
    fun createEngine(model: SttModelInfo): SttEngine
}

 */