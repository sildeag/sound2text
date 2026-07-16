package com.sildeag.sound2text.core.pdf.render

import com.sildeag.sound2text.core.model.PdfPage

interface PdfRenderer {
    suspend fun renderPage(page: PdfPage): ByteArray
}