package com.sildeag.sound2text.core.pdf.io

interface PdfResourceLoader {
    suspend fun load(path: String): ByteArray
}