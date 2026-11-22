package com.sildeag.sound2text.pdfdesktop.di

import com.sildeag.sound2text.core.pdf.extract.PdfTextExtractor // Core interface
import com.sildeag.sound2text.core.pdf.render.PdfRenderer
import com.sildeag.sound2text.pdfdesktop.pdf.extract.DesktopDefaultPdfExtractor
import com.sildeag.sound2text.pdfdesktop.pdf.extract.itext.ITextExtractor
import com.sildeag.sound2text.pdfdesktop.pdf.extract.pdfbox.PdfBoxTextExtractor
import com.sildeag.sound2text.pdfdesktop.pdf.render.pdfbox.PdfBoxRenderer
import com.sildeag.sound2text.pdfdesktop.pdf.render.DesktopUiPdfPageRenderer
import com.sildeag.sound2text.uicommon.pdf.render.UiPdfPageRenderer
import org.koin.dsl.module

val desktopPdfModule = module {
    factory { ITextExtractor() }
    factory { PdfBoxTextExtractor() }
    single<PdfTextExtractor> {
        DesktopDefaultPdfExtractor(
            primaryEngine = get<ITextExtractor>(),
            fallbackEngine = get<PdfBoxTextExtractor>()
        )
    }
    single<PdfRenderer> { PdfBoxRenderer() }
    single<UiPdfPageRenderer> { DesktopUiPdfPageRenderer() }
}
