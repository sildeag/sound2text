package com.sildeag.sound2text.di.pdf

// TODO: remove engine
import
import com.sildeag.sound2text.core.pdf.PdfFormDiscovery
// TODO: remove engine
import
import org.koin.core.module.Module
import org.koin.dsl.module
val pdfDesktopModule: Module = module {
    single<PdfFormDiscovery> { ITextFormDiscovery() }
    single< PdfFormEngine> { ITextPdfEngine() }
}
