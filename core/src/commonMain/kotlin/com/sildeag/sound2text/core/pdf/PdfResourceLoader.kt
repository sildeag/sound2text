package com.sildeag.sound2text.core.pdf

interface PdfResourceLoader {
    suspend fun load(path: String): ByteArray
}