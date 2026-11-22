package com.sildeag.sound2text.pdfandroid.pdf.processor

import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfReader
import com.sildeag.sound2text.core.pdf.model.PdfMetadata
import com.sildeag.sound2text.pdfandroid.pdf.extract.AndroidLoadedUiPdf
import com.sildeag.sound2text.pdfandroid.pdf.extract.AndroidPdfMetadataExtractor
import com.sildeag.sound2text.uicommon.pdf.models.UiPdfPage
import com.sildeag.sound2text.uicommon.pdf.processor.UiPdfProcessor
import java.io.ByteArrayInputStream

class AndroidUiPdfProcessor(
    private val uiPdfProcessor: UiPdfProcessor,
    private val metadataExtractor: AndroidPdfMetadataExtractor
) {

    suspend fun loadUiPdf(bytes: ByteArray): AndroidLoadedUiPdf {
        // 1. Delegate page parsing and rendering to UiPdfProcessor
        val uiPages: MutableList<UiPdfPage> = uiPdfProcessor.loadUiPdf(bytes)

        // 2. Extract Desktop-specific metadata with proper iText resource management
        val metadata = extractMetadataSafely(bytes)

        return AndroidLoadedUiPdf(
            pages = uiPages,
            metadata = metadata
        )
    }

    private fun extractMetadataSafely(bytes: ByteArray): PdfMetadata {
        val reader = PdfReader(ByteArrayInputStream(bytes))
        val pdf = PdfDocument(reader)
        return try {
            metadataExtractor.extract(pdf)
        } finally {
            // Guarantee resource cleanup regardless of extraction failures
            pdf.close()
            reader.close()
        }
    }
}