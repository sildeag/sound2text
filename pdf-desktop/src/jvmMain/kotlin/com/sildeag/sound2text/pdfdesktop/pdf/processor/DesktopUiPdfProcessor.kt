package com.sildeag.sound2text.pdfdesktop.pdf.processor

import com.sildeag.sound2text.pdfdesktop.pdf.extract.DesktopLoadedUiPdf
import com.sildeag.sound2text.uicommon.pdf.processor.UiPdfProcessor

class DesktopUiPdfProcessor(
    private val uiPdfProcessor: UiPdfProcessor,
    private val desktopPdfProcessor: DesktopPdfProcessor
) {
    suspend fun loadUiPdf(bytes: ByteArray): DesktopLoadedUiPdf {
        return desktopPdfProcessor.loadUiPdf(bytes, uiPdfProcessor)
    }
}
