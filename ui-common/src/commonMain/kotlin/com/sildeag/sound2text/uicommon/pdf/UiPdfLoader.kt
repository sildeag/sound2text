package com.sildeag.sound2text.uicommon.pdf

interface uiPdfLoader {
    suspend fun load(path: String): ByteArray
}