package com.sildeag.sound2text.core.pdf.processor

import com.sildeag.sound2text.core.pdf.model.PdfPage

interface PdfProcessor {
    suspend fun loadPdf(bytes: ByteArray): List<PdfPage>
}