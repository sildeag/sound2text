package com.sildeag.sound2text.uidesktop

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sildeag.sound2text.pdf.AppSettings
import com.sildeag.sound2text.core.logging.Logger
import com.sildeag.sound2text.core.storage.StorageService
import com.sildeag.sound2text.stt.SttEngine

object DesktopComposeApp {
    fun launch(
        settings: AppSettings,
        storage: StorageService,
        engine: SttEngine,
        logger: Logger
    ) = application {
        val controller = remember { DesktopSttController(settings, engine,
            logger) }
        Window(
            onCloseRequest = ::exitApplication,
            title = "Sound2Text"
        ) {/*
            DesktopMainScreen(controller)
            */
        }
    }
}
