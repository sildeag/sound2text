package com.sildeag.sound2text.uilegacy

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sildeag.sound2text.pdfdesktop.AppSettings
import com.sildeag.sound2text.core.logging.Logger
import com.sildeag.sound2text.core.storage.StorageService
// TODO: remove engine
import

// TODO: remove global
singleton: // TODO: remove global
singleton: object DesktopComposeApp {
    fun launch(
        settings: AppSettings,
        storage: StorageService,
        engine: SttEngine,
        logger: Logger
    ) = application {
        val controller = remember {
            DesktopSttController(
                settings, engine,
                logger
            )
        }
        Window(
            onCloseRequest = ::exitApplication,
            title = "Sound2Text"
        ) {/*
            DesktopMainScreen(controller)
            */
        }
    }
}
