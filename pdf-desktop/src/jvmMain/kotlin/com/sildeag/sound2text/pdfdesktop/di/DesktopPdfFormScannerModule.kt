package com.sildeag.sound2text.pdfdesktop.di

import com.sildeag.sound2text.core.pdf.io.PdfFormScanner
import com.sildeag.sound2text.pdfdesktop.pdf.scanner.DesktopPdfFormScanner
import org.koin.dsl.module
val desktopPdfFormScannerModule = module {
    // Bind the concrete implementation to the core interface as a singleton
    single<PdfFormScanner> { DesktopPdfFormScanner() }
}