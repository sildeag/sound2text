package com.sildeag.sound2text.core.pdf.render

interface PdfRenderer {
    suspend fun renderPage(page: PdfPage): ByteArray
}