package com.sildeag.sound2text.uicommon.di

import com.sildeag.sound2text.uicommon.pdf.processor.UiPdfProcessor
import org.koin.dsl.module

// In :uicommon DI module
val uiCommonPdfModule = module {
    single {
        UiPdfProcessor(
            coreProcessor = get(), // resolves DesktopPdfProcessor
            pageRenderer = get() // resolves desktop UiPdfPageRenderer implementation
        )
    }
}
