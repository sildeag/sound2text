package com.sildeag.sound2text.pdfandroid.pdf.loader

import android.content.Context
import com.sildeag.sound2text.core.pdf.io.PdfResourceLoader

class AndroidAndroidPdfResourceLoader(
    private val context: Context
) : PdfResourceLoader {

    override suspend fun loadBytes(path: String): ByteArray {
        return context.assets.open(path).use { it.readBytes() }
    }

    override suspend fun load(path: String): ByteArray {
        val bytes = loadBytes(path)
        return bytes
    }
}