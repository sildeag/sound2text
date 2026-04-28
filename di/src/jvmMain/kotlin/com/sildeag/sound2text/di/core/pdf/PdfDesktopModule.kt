package com.sildeag.sound2text.di.core.pdf

import com.sildeag.sound2text.core.pdf.PdfFormEngine
import com.sildeag.sound2text.core.pdf.PdfFormDiscovery
import com.sildeag.sound2text.pdfdesktop.itext.ITextPdfEngine
import org.koin.core.module.Module
import org.koin.dsl.module
val pdfDesktopModule: Module = module {
    single<PdfFormDiscovery> { ITextFormDiscovery() }
    single< PdfFormEngine> { ITextPdfEngine() }
}