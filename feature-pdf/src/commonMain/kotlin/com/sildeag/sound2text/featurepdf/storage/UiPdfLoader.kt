package com.sildeag.sound2text.featurepdf.storage

import com.sildeag.sound2text.core.pdf.io.PdfResourceLoader
import com.sildeag.sound2text.core.pdf.processor.PdfProcessor
import com.sildeag.sound2text.core.pdf.render.PdfRenderer
import com.sildeag.sound2text.uicommon.mappers.PdfUiMapper
import com.sildeag.sound2text.featurepdf.models.UiPdfPage
import com.sildeag.sound2text.featurepdf.models.UiPdfDocument




class UiPdfLoader(
    private val resourceLoader: PdfResourceLoader,
    private val processor: PdfProcessor,
    private val renderer: PdfRenderer,
    private val mapper: PdfUiMapper<UiPdfPage> = DefaultPdfUiMapper()
) {
    suspend fun load(path: String): UiPdfDocument {

        val bytes = resourceLoader.load(path)

        val pdfDocument = processor.loadPdf(bytes)

        val uiPages = pdfDocument.pages.map { corePage ->

            val rendered = renderer.render(corePage.index)

            mapper.map(
                corePage,
                rendered.bitmap,
                rendered.width,
                rendered.height
            )
        }

        return UiPdfDocument(
            name = pdfDocument.name,
            pageCount = pdfDocument.pages.size,
            pages = uiPages,
            metadata = pdfDocument.metadata
        )
    }
}

