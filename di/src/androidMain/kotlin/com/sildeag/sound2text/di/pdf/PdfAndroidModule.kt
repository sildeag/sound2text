package com.sildeag.sound2text.di.core.pdf

import com.sildeag.sound2text.core.pdf.PdfFormEngine
import com.sildeag.sound2text.core.pdf.PdfFormDiscovery
import com.sildeag.sound2text.pdfandroid.PdfAndroidEngine
import com.sildeag.sound2text.pdfandroid.PdfAndroidFormDiscovery
import org.koin.core.module.Module
import org.koin.dsl.module
val pdfAndroidModule: Module = module {
    single<PdfFormDiscovery> { PdfAndroidFormDiscovery() }
    single< PdfFormEngine> { PdfAndroidEngine() }
}


