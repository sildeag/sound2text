package com.sildeag.sound2text.core.di

import com.sildeag.sound2text.core.service.pdf.DefaultPdfWriterFactory
import com.sildeag.sound2text.core.service.pdf.PdfWriterFactory
import org.koin.dsl.module

val pdfWriterModule = module {
    single<PdfWriterFactory> { DefaultPdfWriterFactory() }
}
