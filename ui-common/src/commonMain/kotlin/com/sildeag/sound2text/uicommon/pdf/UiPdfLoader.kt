package com.sildeag.sound2text.uicommon.pdf

import com.sildeag.sound2text.core.pdf.io.PdfResourceLoader
import com.sildeag.sound2text.core.pdf.processor.PdfProcessor
import com.sildeag.sound2text.core.pdf.render.PdfRenderer
import com.sildeag.sound2text.uicommon.mappers.DefaultPdfUiMapper
import com.sildeag.sound2text.uicommon.mappers.PdfUiMapper
import com.sildeag.sound2text.uicommon.models.UiPdfPage
import com.sildeag.sound2text.core.pdf.model.PdfDocument

class UiPdfLoader(
    private val resourceLoader: PdfResourceLoader,
    private val processor: PdfProcessor,
    private val renderer: PdfRenderer,
    private val mapper: PdfUiMapper<UiPdfPage> = DefaultPdfUiMapper()
) {
    suspend fun load(path: String): PdfDocument {
        val bytes = resourceLoader.load(path)
        val corePages = processor.loadPdf(bytes)
        val uiPages = corePages.map { corePage ->
            val rendered = renderer.render(corePage.index)
            mapper.map(corePage, rendered.bitmap, rendered.width, rendered.height)
        }
        return PdfDocument(uiPages)
    }
}

