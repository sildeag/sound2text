package com.sildeag.sound2text.pdfdesktop.di

import com.sildeag.sound2text.core.pdf.io.PdfResourceLoader
import com.sildeag.sound2text.pdfdesktop.pdf.loader.DesktopPdfResourceLoader
import org.koin.dsl.module
val desktopPdfResourceLoaderModule = module {
    // Bind the concrete implementation to the core interface as a singleton
    single<PdfResourceLoader> { DesktopPdfResourceLoader() }
}