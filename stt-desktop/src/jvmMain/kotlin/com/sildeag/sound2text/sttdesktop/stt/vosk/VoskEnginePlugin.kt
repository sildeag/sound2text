package com.sildeag.sound2text.sttdesktop.stt.vosk

import com.sildeag.sound2text.core.stt.engine.SttEngine
import com.sildeag.sound2text.core.stt.engine.SttEnginePlugin
import com.sildeag.sound2text.core.stt.model.ModelDescriptor
import com.sildeag.sound2text.core.stt.model.SttModelInfo
import java.io.File
class VoskEnginePlugin(
    private val engineFactory: (String, String?) -> SttEngine
) : SttEnginePlugin {
    override val engineName = "vosk"
    override val displayName = "Vosk"
    override fun availableModels(): List<SttModelInfo> = emptyList()
    override fun discoverModels(basePath: String):
            List<ModelDescriptor> {
        val dir = File(basePath)
        if (!dir.exists()) return emptyList()
        return dir.listFiles()
            ?.filter { it.extension == "zip" || it.extension ==
                    "model" }
            ?.map {
                ModelDescriptor(
                    id = it.nameWithoutExtension,
                    displayName = it.nameWithoutExtension,
                    path = it.absolutePath,
                    language = null,
                    engineName = engineName
                )
            } ?: emptyList()
    }
    override fun createEngine(model: SttModelInfo): SttEngine =
        engineFactory(model.path, model.language)
}
