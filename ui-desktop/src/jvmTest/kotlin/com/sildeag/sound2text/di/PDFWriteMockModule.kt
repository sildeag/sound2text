package com.sildeag.sound2text.di

import com.sildeag.sound2text.service.pdf.PdfWriterFactory
import org.koin.dsl.module

object PDFWriterMockModule {
    val module = module(override = true) {
        single<PdfWriterFactory> { FailingPdfWriter() }
    }
}
