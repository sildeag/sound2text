package com.sildeag.sound2text.core.pdf

interface PdfProcessor {
    suspend fun loadPdf(bytes: ByteArray): PdfDocument
}
