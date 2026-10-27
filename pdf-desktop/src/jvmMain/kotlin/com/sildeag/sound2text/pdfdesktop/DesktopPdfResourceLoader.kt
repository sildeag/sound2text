package com.sildeag.sound2text.pdfdesktop

import com.sildeag.sound2text.core.pdf.io.PdfResourceLoader

class DesktopPdfResourceLoader : PdfResourceLoader {
    override suspend fun load(path: String): ByteArray =
        javaClass.classLoader.getResourceAsStream(path)?.readBytes()
            ?: error("PDF not found: $path")
}
