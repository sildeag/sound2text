package com.sildeag.sound2text.di

import com.sildeag.sound2text.service.logic.PulseLogic
import com.sildeag.sound2text.service.pdf.PdfWriterFactory
import com.sildeag.sound2text.service.recognizer.MockMicRecognizer
import com.sildeag.sound2text.service.recognizer.Recognizer
import io.mockk.mockk
import org.koin.core.qualifier.named
import org.koin.dsl.module

object TestModules {
    val base = module {
        includes(appSettingsModule, loggingModule, uiModule)
    }
    val recognizerMock = module(override = true) {
        single<Recognizer> { MockMicRecognizer() }
    }
    val pdfWriterMock = module(override = true) {
        single<PdfWriterFactory> { FailingPdfWriter() }
    }
    val pulseLogicTest = module(override = true) {
        single<PulseLogic> { TestPulseLogic() }
    }
    val callbackMocks = module {
        single<(String) -> Unit>(named("onText")) { mockk(relaxed = true)
        }
        single<(Boolean) -> Unit>(named("onMic")) { mockk(relaxed = true)
        }
        single<(String) -> Unit>(named("onPulse")) { mockk(relaxed =
            true) }
    }


    val fullStack = module {
        includes(
            appSettingsModule,
            audioModule,
            loggingModule,
            resourceModule,
            noteModuleDev,
            noteEditorModule,
            pulseModule,
            recognizerModule,
            pdfWriterModule,
            uiModule
        )
    }
}
