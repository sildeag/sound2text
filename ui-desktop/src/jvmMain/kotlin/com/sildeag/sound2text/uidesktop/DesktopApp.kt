package com.sildeag.sound2text.uidesktop


import androidx.compose.ui.window.application
import com.sildeag.sound2text.di.common.pdfCommonModule
import com.sildeag.sound2text.di.pdf.pdfPlatformModule
import org.koin.core.// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context.startKoin
import org.koin.dsl.koinApplication
fun main() {
    val koinApp = koinApplication {
        modules(
            pdfCommonModule,
            pdfPlatformModule,
            // coreModule,
            // uiModule,
            // loggerModule,
        )
    }
    startKoin { koinApp.modules }
    application {
        DesktopRoot()
    }
}
