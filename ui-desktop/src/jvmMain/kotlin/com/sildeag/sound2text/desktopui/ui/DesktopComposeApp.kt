package com.sildeag.sound2text.desktopui.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.singleWindowApplication
import com.sildeag.sound2text.config.AppSettings
import com.sildeag.sound2text.logging.Logger
import com.sildeag.sound2text.storage.StorageService
import com.sildeag.sound2text.stt.SpeechToTextService

object DesktopComposeApp {
    fun launch(
        storage: StorageService,
        stt: SpeechToTextService,
        settings: AppSettings,
        logger: Logger
    ) {
        logger.info("Launching Compose Desktop UI")
        singleWindowApplication(title = "Sound2Text Desktop") {
            AppRoot(storage, stt, settings)
        }
    }
}
@Composable
private fun AppRoot(
    storage: StorageService,
    stt: SpeechToTextService,
    settings: AppSettings
) {
    Text("Sound2Text Desktop (Compose UI)")
}
