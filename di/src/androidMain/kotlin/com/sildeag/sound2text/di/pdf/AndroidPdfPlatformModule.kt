package com.sildeag.sound2text.di.pdf

import com.sildeag.sound2text.core.pdf.PdfFormDiscovery
import org.koin.dsl.module

actual val pdfPlatformModule = module {
    single<PdfFormDiscovery> { PdfiumFormDiscovery() }
}
