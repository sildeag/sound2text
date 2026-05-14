package com.sildeag.sound2text.desktop
import com.sildeag.sound2text.core.config.AppSettings
import com.sildeag.sound2text.core.config.SettingsLoader
import com.sildeag.sound2text.core.common.logging.Logger
import com.sildeag.sound2text.core.storage.StorageService
import com.sildeag.sound2text.sttdesktop.SttEngine
import com.sildeag.sound2text.sttdesktop.SttService
import com.sildeag.sound2text.sttdesktop.SttConfig
import com.sildeag.sound2text.sttdesktop.di.SttCommonModule
import com.sildeag.sound2text.sttdesktop.jvm.di.SttJvmModule
import com.sildeag.sound2text.uidesktop.desktopModule
import com.sildeag.sound2text.uidesktop.ui.DesktopComposeApp
import com.sildeag.sound2text.uidesktop.ui.fxml.FxmlLauncher
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject
fun main() {
// Load settings from your config module
    val settings: AppSettings = SettingsLoader.load()
    println("Sound2Text starting ${settings.ui.type}")
// Start DI with platform-specific modules
    startKoin {
        modules(
            desktopModule(settings), // your existing desktop module
            SttCommonModule, // shared STT API
            SttJvmModule // JVM Vosk engine
        )
    }
// Resolve core services
    val logger: Logger by inject(Logger::class.java)
    logger.info("Sound2Text starting in ${settings.mode} mode on ${settings.platform}")
        val storage: StorageService by inject(StorageService::class.java)
// Resolve the new STT engine and create a service instance
    val engine: SttEngine by inject(SttEngine::class.java)
    val stt: SttService = engine.loadModel(
        SttConfig(
            modelPath = settings.stt.model,
            language = settings.stt.language
        )
    )
    logger.info("Sound2Text starting ${settings.ui.type}")
// UI selection (Compose or FXML)
    when (settings.ui.type) {
        "compose" -> DesktopComposeApp.launch(storage, stt, settings,
            logger)
        "fxml" -> {
            logger.warning("FXML UI testing")
            FxmlLauncher.launch()
        }
        else -> {
            logger.error("Unsupported UI type: ${settings.ui.type}")
            DesktopComposeApp.launch(storage, stt, settings, logger)
        }
    }
}


/*
fun main() {
    // Load settings from your config module
    val settings: AppSettings = SettingsLoader.load()
    println("Sound2Text starting ${settings.ui.type}")
    // Start DI with platform-specific modules
    startKoin {
        modules(
            desktopModule(settings), // your existing desktop module
            SttCommonModule, // shared STT API
            SttJvmModule // JVM Vosk engine
        )
    }
    // Resolve core services
    val logger: Logger by inject(Logger::class.java)
    logger.info("Sound2Text starting in ${settings.mode} mode on ${settings.platform}")
        val storage: StorageService by inject(StorageService::class.java)
    // Resolve the new STT engine and create a service instance
    val engine: SttEngine by inject(SttEngine::class.java)
    val stt: SttService = engine.loadModel(
        SttConfig(
            modelPath = settings.stt.model,
            language = settings.stt.language
        )
    )
    logger.info("Sound2Text starting ${settings.ui.type}")
    // UI selection (Compose or FXML)
    when (settings.ui.type) {
        "compose" -> DesktopComposeApp.launch(storage, stt, settings,
            logger)
        "fxml" -> {
            logger.warning("FXML UI testing")
            FxmlLauncher.launch()
        }
        else -> {
            logger.error("Unsupported UI type: ${settings.ui.type}")
            DesktopComposeApp.launch(storage, stt, settings, logger)
        }
    }
}
*/

/*
import com.sildeag.sound2text.core.logging.Logger
import com.sildeag.sound2text.config.AppSettings
import com.sildeag.sound2text.config.SettingsLoader
import com.sildeag.sound2text.core.storage.StorageService
import com.sildeag.sound2text.core.stt.SpeechToTextService
import org.koin.core.context.startKoin
import com.sildeag.sound2text.desktop.ui.DesktopComposeApp
import com.sildeag.sound2text.desktop.ui.fxml.FxmlLauncher
import org.koin.java.KoinJavaComponent.inject

fun main() {
    val settings: AppSettings = SettingsLoader.load()
    println("Sound2Text starting ${settings.ui.type}")
    startKoin {
        modules(desktopModule(settings))
    }

    val logger: Logger by inject(Logger::class.java)
    logger.info("Sound2Text starting in ${settings.mode} mode on ${settings.platform}")
        val storage: StorageService by inject(StorageService::class.java)
    val stt: SpeechToTextService by
    inject(SpeechToTextService::class.java)
    logger.info("Sound2Text starting ${settings.ui.type}")
    when (settings.ui.type   ) {
        "compose" -> DesktopComposeApp.launch(storage, stt, settings, logger)
        "fxml" -> {
            logger.warning("FXML UI testing")
            FxmlLauncher.launch()
        }
        else -> {
            logger.error("Unsupported UI type: ${settings.ui.type}")
            DesktopComposeApp.launch(storage, stt, settings, logger)
        }
    }
}
*/



/*
package com.sildeag.sound2text.desktop
import com.sildeag.sound2text.platform.SettingsLoader
import com.sildeag.sound2text.storage.StorageService
import com.sildeag.sound2text.desktop.ui.DesktopComposeApp
import com.sildeag.sound2text.desktop.ui.DesktopFxmlApp
import com.sildeag.sound2text.config.AppSettings
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject
fun main() {
    // 1. Load config.json
    val settings = SettingsLoader.load()
    // 2. Start DI with settings
    startKoin {
        modules(desktopModule(settings))
    }
    /*
    val desktopModule = { settings: AppSettings ->
        module {
            single { settings }
            single<StorageService> { DesktopStorageService() }
            // Add more desktop-specific services here
        }
    }

     */
    // 3. Resolve dependencies
    val storage: StorageService by inject(StorageService::class.java)
    // 4. Launch UI
    when (settings.uiMode) {
        "compose" -> DesktopComposeApp.launch(storage, settings)
        "fxml" -> DesktopFxmlApp.launch(storage, settings)
        else -> error("Unknown UI mode: ${settings.uiMode}")
    }
}
*/