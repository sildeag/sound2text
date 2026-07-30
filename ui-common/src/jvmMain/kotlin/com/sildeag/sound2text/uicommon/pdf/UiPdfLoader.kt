package com.sildeag.sound2text.uicommon.pdf

import com.sildeag.sound2text.uicommon.pdf.
import com.sildeag.sound2text.core.pdf.io.PdfResourceLoader
import com.sildeag.sound2text.core.pdf.processor.PdfProcessor
import com.sildeag.sound2text.core.pdf.render.PdfRenderer

class UiPdfLoader(
    private val resourceLoader: PdfResourceLoader,
    private val processor: PdfProcessor,
    private val renderer: PdfRenderer, // Android or Desktop
    private val mapper: PdfUiMapper
) {
    suspend fun load(path: String): UiPdfDocument {
        val bytes = resourceLoader.load(path)
        val corePages = processor.loadPdf(bytes)
        val uiPages = corePages.map { corePage ->
            val rendered = renderer.render(corePage.index)
            mapper.map(corePage, rendered.bitmap, rendered.width,
                rendered.height)
        }
        return UiPdfDocument(uiPages)
    }
}
