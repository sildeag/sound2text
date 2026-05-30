package com.sildeag.sound2text.appcommon

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
