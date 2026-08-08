package com.sildeag.sound2text.uicommon.di

import com.sildeag.sound2text.core.pdf.model.DefaultPdfUiMapper
import com.sildeag.sound2text.core.pdf.model.PdfUiMapper
import com.sildeag.sound2text.uicommon.pdf.PdfViewModel
import com.sildeag.sound2text.uicommon.pdf.PdfState
import com.sildeag.sound2text.uicommon.pdf
import org.koin.dsl.module
val uiPdfModule = module {
    factory<PdfUiMapper> { DefaultPdfUiMapper() }
    factory { UiPdfLoader(get(), get()) }
    factory { PdfState() }
    factory { PdfViewModel(get(), get()) }
}
