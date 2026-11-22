package com.sildeag.sound2text.pdfdesktop.di

import com.sildeag.sound2text.core.pdf.processor.PdfProcessor
import com.sildeag.sound2text.pdfdesktop.pdf.extract.DesktopPdfMetadataExtractor
import com.sildeag.sound2text.pdfdesktop.pdf.processor.DesktopPdfProcessor
import com.sildeag.sound2text.pdfdesktop.pdf.processor.DesktopUiPdfProcessor
import org.koin.dsl.module

val desktopPdfProcessorModule = module {
    // 1. Register the metadata extractor
    factory { DesktopPdfMetadataExtractor() }
    // 2. Register core PdfProcessor implementation for desktop with metadata extractor injected
    single<PdfProcessor> { DesktopPdfProcessor(metadataExtractor = get()) }
    single { DesktopPdfProcessor(metadataExtractor = get()) }
    // 3. Register your main UI PDF processor, resolving its dependencies via get()
    single {
        DesktopUiPdfProcessor(
            uiPdfProcessor = get(),
            desktopPdfProcessor = get()
        )
    }
}
