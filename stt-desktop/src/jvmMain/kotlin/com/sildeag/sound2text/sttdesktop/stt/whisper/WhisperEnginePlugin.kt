package com.sildeag.sound2text.sttdesktop.stt.whisper

import com.sildeag.sound2text.core.stt.engine.SttEngine
import com.sildeag.sound2text.core.stt.engine.SttEnginePlugin
import com.sildeag.sound2text.core.stt.model.ModelDescriptor
import com.sildeag.sound2text.core.stt.model.SttModelInfo
import java.io.File
class WhisperEnginePlugin(
    private val engineFactory: (String, String?) -> SttEngine
) : SttEnginePlugin {
    override val engineName = "whisper"
    override val displayName = "Whisper"
    override fun availableModels(): List<SttModelInfo> = emptyList()
    override fun discoverModels(basePath: String):
            List<ModelDescriptor> {
        val dir = File(basePath)
        if (!dir.exists()) return emptyList()
        return dir.listFiles()
            ?.filter { it.extension.lowercase() in listOf("bin",
                "ggml", "gguf") }
            ?.map {
                ModelDescriptor(
                    id = it.nameWithoutExtension,
                    displayName = it.nameWithoutExtension,
                    path = it.absolutePath,
                    language = detectLanguage(it.name),
                    engineName = engineName
                )
            } ?: emptyList()
    }
    private fun detectLanguage(name: String): String? =
        when {
            "en" in name.lowercase() -> "en"
            "es" in name.lowercase() -> "es"
            else -> null
        }
    override fun createEngine(model: SttModelInfo): SttEngine =
        engineFactory(model.path, model.language)
}