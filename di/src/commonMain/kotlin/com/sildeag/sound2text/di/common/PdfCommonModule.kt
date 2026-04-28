package com.sildeag.sound2text.di.common

import com.sildeag.sound2text.core.pdf.PdfFormEngine
import com.sildeag.sound2text.core.pdf.PdfFormDiscovery
import org.koin.core.module.Module
import org.koin.dsl.module
val pdfCommonModule: Module = module {
    // interfaces only; implementations bound in platform modules
    single<PdfFormDiscovery> { get() } // via platform module
    single<PdfFormEngine> { get() }
}
