package com.sildeag.sound2text.core.pdf.processor

interface PdfProcessor {
    suspend fun loadPdf(bytes: ByteArray): List<PdfPage>
}