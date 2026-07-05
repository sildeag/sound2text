package com.sildeag.sound2text.pdfandroid.pdf

import android.content.Context
import com.sildeag.sound2text.core.pdf.PdfResourceLoader

class AndroidPdfResourceLoader(
    private val context: Context
) : PdfResourceLoader {
    override suspend fun load(path: String): ByteArray =
        context.assets.open(path).readBytes()
}
