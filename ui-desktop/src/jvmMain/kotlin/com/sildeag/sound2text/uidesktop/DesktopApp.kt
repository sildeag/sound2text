package com.sildeag.sound2text.uidesktop


import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sildeag.sound2text.di.common.coreModule
import com.sildeag.sound2text.di.common.pdfCommonModule
import com.sildeag.sound2text.di.pdf.pdfPlatformModule
import org.koin.core.context.startKoin

/* Simplest way
fun main() = ap
plication {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Sound2Text Desktop"
    ) {
        val stt = rememberSttService()
        RecordingScreen(stt)
    }
}
*/
import org.koin.dsl.koinApplication
fun main() {
    val koinApp = koinApplication {
        modules(
            pdfCommonModule,
            //pdfPlatformModule,
            coreModule,
            //uiModule,
            //loggerModule,
        )
    }
    startKoin { koinApp.modules }
    application {
        DesktopRoot()
    }
}
