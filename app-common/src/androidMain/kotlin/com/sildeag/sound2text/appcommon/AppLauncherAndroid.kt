package com.sildeag.sound2text.appcommon

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
