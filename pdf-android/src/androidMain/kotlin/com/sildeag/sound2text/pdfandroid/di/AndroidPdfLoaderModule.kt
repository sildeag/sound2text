package com.sildeag.sound2text.pdfandroid.di

import com.sildeag.sound2text.core.pdf.io.PdfResourceLoader
import com.sildeag.sound2text.pdfandroid.pdf.loader.AndroidAndroidPdfResourceLoader
import org.koin.dsl.module

val androidPdfLoaderModule = module {
    single<PdfResourceLoader> { AndroidAndroidPdfResourceLoader(get()) }
}
