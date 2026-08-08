package com.sildeag.sound2text.uicommon.di

import com.sildeag.sound2text.core.pdf.PdfRenderer
import com.sildeag.sound2text.core.pdf.PdfTextExtractor
import com.sildeag.sound2text.core.pdf.render.PdfRenderer
import com.sildeag.sound2text.uidesktop.pdf.PdfBoxTextExtractor
import com.sildeag.sound2text.uidesktop.pdf.DesktopPdfRenderer
import com.sildeag.sound2text.uicommon.pdf.UiPdfLoader
import org.koin.dsl.module
val pdfDesktopModule = module {
    // Platform PDF extractor (PdfBox)
    factory<PdfTextExtractor> { PdfBoxTextExtractor() }
    // Platform PDF renderer (IText or PdfBox rendering)
    factory<PdfRenderer> { DesktopPdfRenderer() }
    // Shared UI loader (depends on extractor + renderer)
    factory { UiPdfLoader(get(), get()) }
}
