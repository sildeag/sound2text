package com.sildeag.sound2text.uilegacy.di

import com.sildeag.sound2text.service.pdf.PdfWriterFactory
import org.koin.dsl.module

// TODO: remove global
singleton: // TODO: remove global
singleton: object PDFWriterMockModule {
    val module = module(override = true) {
        single<PdfWriterFactory> { FailingPdfWriter() }
    }
}
