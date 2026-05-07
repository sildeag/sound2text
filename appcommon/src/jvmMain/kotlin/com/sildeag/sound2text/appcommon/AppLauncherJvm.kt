package com.sildeag.sound2text.appcommon

import androidx.compose.ui.window.application
import com.sildeag.sound2text.core.pdf.PdfFormDiscovery
import com.sildeag.sound2text.di.common.*
import com.sildeag.sound2text.di.desktop.*
import com.sildeag.sound2text.core.di.coreModule
import com.sildeag.sound2text.di.stt.sttDesktopModule
import com.sildeag.sound2text.di.ui.uiDesktopModule
import com.sildeag.sound2text.uicommon.AppRoot
import com.sildeag.sound2text.uicommon.nav.NavigationState
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.getKoin
actual fun runApp() {
    startKoin {
        modules(
            coreModule,
            uiCommonModule,
            pdfCommonModule,
            sttCommonModule,
            com.sildeag.sound2text.di.pdf.pdfDesktopModule,
            sttDesktopModule,
            uiDesktopModule
        )
    }
    val koin = getKoin()
    val discoveries: List<PdfFormDiscovery> =
        koin.getAll(PdfFormDiscovery::class.java)
    application {
        val nav = NavigationState()
        AppRoot(nav = nav, discoveries = discoveries)
    }
}
