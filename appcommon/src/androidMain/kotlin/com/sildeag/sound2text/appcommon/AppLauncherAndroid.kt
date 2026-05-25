package com.sildeag.sound2text.appcommon

import androidx.compose.runtime.Composable
import com.sildeag.sound2text.core.common.logging.Logger
import com.sildeag.sound2text.settings.AppSettings
import com.sildeag.sound2text.stt.SpeechToTextService
import com.sildeag.sound2text.uicommon.AppRoot
import com.sildeag.sound2text.uicommon.nav.NavigationState
import org.koin.android.ext.android.getKoin
import org.vosk.android.StorageService

@Composable
actual fun runApp() {
    val koin = getKoin()
    val logger: Logger = koin.get()
    val storage: StorageService = koin.get()
    val stt: SpeechToTextService = koin.get()
    val settings: AppSettings = koin.get()
    logger.info("Sound2Text Android starting in ${settings.Loglevel} LogLevel")
        val nav = NavigationState()
    AppRoot(
        nav = nav,
        discoveries = emptyList(), // Android PDF discovery wired
        later
    )
}

/*
import androidx.compose.runtime.Composable
import com.sildeag.sound2text.core.pdf.PdfFormDiscovery
import com.sildeag.sound2text.uicommon.AppRoot
import com.sildeag.sound2text.uicommon.nav.NavigationState
import org.koin.android.ext.android.getKoin

/**
 * Android's actual implementation is a Composable.
 * MainActivity calls runApp() inside setContent { ... }.
 */
@Composable
actual fun runApp() {
    val koin = getKoin()
    val discoveries: List<PdfFormDiscovery> = koin.getAll()

    val nav = NavigationState()
    AppRoot(nav = nav, discoveries = discoveries)
}
*/
