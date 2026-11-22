package com.sildeag.sound2text.pdfandroid.di

import com.sildeag.sound2text.core.pdf.extract.PdfTextExtractor
import com.sildeag.sound2text.pdfandroid.pdf.extract.AndroidDefaultPdfExtractor
import com.sildeag.sound2text.pdfandroid.pdf.extract.itext.ITextExtractor
import com.sildeag.sound2text.pdfandroid.pdf.extract.pdfbox.PdfBoxTextExtractor
import org.koin.dsl.module

val androidPdfModule = module {
    factory { PdfBoxTextExtractor() }
    factory { ITextExtractor() }
    single<PdfTextExtractor> {
        AndroidDefaultPdfExtractor(
            primaryEngine = get<PdfBoxTextExtractor>(),
            fallbackEngine = get<ITextExtractor>()
        )
    }
}