package com.sildeag.sound2text.uilegacy.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.singleWindowApplication
import com.sildeag.sound2text.pdfdesktop.AppSettings
import com.sildeag.sound2text.core.common.logging.Logger
import com.sildeag.sound2text.core.storage.StorageService

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
