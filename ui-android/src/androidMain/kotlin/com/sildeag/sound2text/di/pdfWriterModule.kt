package com.sildeag.sound2text.di

import com.sildeag.sound2text.service.pdf.DefaultPdfWriterFactory
import com.sildeag.sound2text.service.pdf.PdfWriterFactory
import org.koin.dsl.module

val pdfWriterModule = module {
    single<PdfWriterFactory> { DefaultPdfWriterFactory() }
}
