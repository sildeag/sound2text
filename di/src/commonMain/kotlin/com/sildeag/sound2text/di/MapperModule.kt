package com.sildeag.sound2text.di

import org.koin.dsl.module
import com.sildeag.sound2text.core.pdf.model.DefaultPdfUiMapper

val mapperModule = module {
    factory<PdfUiMapper<Any>> { DefaultPdfUiMapper() }
}
