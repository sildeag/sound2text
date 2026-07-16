package com.sildeag.sound2text.core.pdf.extract

interface PdfTextExtractor {
    suspend fun extractText(bytes: ByteArray): List<String>
}
