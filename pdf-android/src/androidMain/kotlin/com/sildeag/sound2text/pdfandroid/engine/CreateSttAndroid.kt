package com.sildeag.sound2text.pdfandroid.engine

class CreateSttAndroid(
    private val plugins: List<SttEnginePlugin>
) {
    fun create(config: SttConfig): SttService {
        val plugin = plugins.first { it.engineName ==
                config.engineName }
        val factory = plugin.createFactory()
        val engine = factory.load(config)
        return AndroidSttService(engine)
    }
}