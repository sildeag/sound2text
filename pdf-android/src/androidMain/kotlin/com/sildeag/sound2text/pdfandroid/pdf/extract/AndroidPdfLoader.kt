package com.sildeag.sound2text.pdfandroid.pdf.extract

import com.sildeag.sound2text.core.pdf.api.PdfMetadataExtractor
import com.sildeag.sound2text.core.pdf.model.PdfMetadata
import com.sildeag.sound2text.uicommon.pdf.models.UiPdfPage
import com.sildeag.sound2text.uicommon.pdf.processor.UiPdfProcessor

class AndroidPdfProcessor(
    private val uiPdfProcessor: UiPdfProcessor,
    private val metadataExtractor: PdfMetadataExtractor // inject DesktopPdfMetadataExtractor
) {
    suspend fun loadUiPdf(bytes: ByteArray): Pair<List<UiPdfPage>, PdfMetadata> {
        val pages = uiPdfProcessor.loadUiPdf(bytes)
        val metadata = metadataExtractor.extract(bytes)
        return Pair(pages, metadata)
    }
}
