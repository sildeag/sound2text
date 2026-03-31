package com.sildeag.sound2text.uidesktop

import com.sildeag.sound2text.core.logging.Logger
import com.sildeag.sound2text.sttdesktop.SttConfig
import com.sildeag.sound2text.sttdesktop.SttEngine
import com.sildeag.sound2text.sttdesktop.SttService
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.getKoin
fun resolveEngineOrExit(
    engineName: String,
    logger: Logger,
    config: SttConfig
): SttService? {
    val engine: SttEngine = try {
        getKoin().get(qualifier = named(engineName))
    } catch (e: Exception) {
        logger.error(
            "STT engine '$engineName' not found in DI.  Exiting gracefully.")
            return null
    }
    return try {
        engine.loadModel(config)
    } catch (e: Exception) {
        logger.error(
            "Failed to initialize STT engine '$engineName': ${e.message}")
        null
    }
}
