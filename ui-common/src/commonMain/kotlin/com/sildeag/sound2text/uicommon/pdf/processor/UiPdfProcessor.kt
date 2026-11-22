package com.sildeag.sound2text.uicommon.pdf.processor

import com.sildeag.sound2text.core.pdf.model.PdfPage
import com.sildeag.sound2text.core.pdf.processor.PdfProcessor
import com.sildeag.sound2text.uicommon.pdf.models.UiPdfPage
import com.sildeag.sound2text.uicommon.pdf.render.UiPdfPageRenderer

class UiPdfProcessor(
    private val coreProcessor: PdfProcessor,
    private val pageRenderer: UiPdfPageRenderer
) {
    suspend fun loadUiPdf(bytes: ByteArray): MutableList<UiPdfPage> {
        // 1. Delegate core metadata/text extraction to :core
        val domainPages: List<PdfPage> = coreProcessor.loadPdf(bytes)

        // 2. Map domain pages into UiPdfPage models using pageRenderer
        return domainPages.map { corePage ->
            val renderedBitmap = pageRenderer.renderBitmapForPage(bytes, corePage.index)

            UiPdfPage(
                index = corePage.index,
                pageNumber = corePage.pageNumber,
                text = corePage.text,
                bitmap = renderedBitmap,
                width = corePage.width,
                height = corePage.height
            )
        }.toMutableList()
    }
}