package com.sildeag.sound2text.core.repository

interface PdfRepository {
    suspend fun loadPdf(path: String): ByteArray
}