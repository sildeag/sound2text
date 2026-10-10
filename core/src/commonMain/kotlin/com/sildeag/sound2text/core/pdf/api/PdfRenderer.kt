package com.sildeag.sound2text.core.pdf.api

import com.sildeag.sound2text.core.pdf.model.PdfPage

interface PdfRenderer {
    suspend fun render(page: PdfPage, width: Int):
            ByteArray
}