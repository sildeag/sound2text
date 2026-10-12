package com.sildeag.sound2text.uicommon

import PdfViewModel
import androidx.compose.runtime.*
import com.sildeag.sound2text.uicommon.screens.RecordingScreen
import com.sildeag.sound2text.uicommon.screens.SettingsScreen


enum class AppScreen {
    Recording,
    Pdf,
    Settings
}

@Composable
fun AppRoot(
    pdfViewModel: PdfViewModel,
    renderPdfScreen: @Composable (String, Int, PdfViewModel) -> Unit
) {
    var currentScreen by remember { mutableStateOf(AppScreen.Recording) }

    when (currentScreen) {

        AppScreen.Recording -> RecordingScreen(
            onOpenPdf = { path, page ->
                currentScreen = AppScreen.Pdf
                renderPdfScreen(path, page, pdfViewModel)
            },
            onOpenSettings = {
                currentScreen = AppScreen.Settings
            }
        )

        AppScreen.Pdf -> renderPdfScreen(
            "placeholder.pdf",
            0,
            pdfViewModel
        )

        AppScreen.Settings -> SettingsScreen(
            onBack = { currentScreen = AppScreen.Recording }
        )
    }
}
