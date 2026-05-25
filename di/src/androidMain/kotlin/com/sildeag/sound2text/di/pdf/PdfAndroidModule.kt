package com.sildeag.sound2text.di.pdf

// TODO: remove engine
import
import com.sildeag.sound2text.core.pdf.PdfFormDiscovery
// TODO: remove engine
import
import com.sildeag.sound2text.pdfandroid.PdfAndroidFormDiscovery
import org.koin.core.module.Module
import org.koin.dsl.module
val pdfAndroidModule: Module = module {
    single<PdfFormDiscovery> { PdfAndroidFormDiscovery() }
    single< PdfFormEngine> { PdfAndroidEngine() }
}


