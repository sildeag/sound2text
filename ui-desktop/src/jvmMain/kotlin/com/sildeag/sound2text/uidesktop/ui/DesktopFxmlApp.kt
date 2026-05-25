package com.sildeag.sound2text.uidesktop.ui

import com.sildeag.sound2text.pdfdesktop.AppSettings
import com.sildeag.sound2text.core.common.logging.Logger
import com.sildeag.sound2text.core.storage.StorageService

// TODO: remove global
singleton: // TODO: remove global
singleton: object DesktopFxmlApp {
    fun launch(
        storage: StorageService,
        stt: SpeechToTextService,
        settings: AppSettings,
        logger: Logger
    ) {
        logger.warning("DesktopFxmlApp.launch called, but FXML UI is not implemented.")
                // You can add JavaFX Application integration here later if desired.
    }
}
