package com.sildeag.sound2text.pdfdesktop.pdf.loader

import com.sildeag.sound2text.core.pdf.io.PdfResourceLoader

class DesktopPdfResourceLoader : PdfResourceLoader {

    override suspend fun loadBytes(path: String): ByteArray {
        return javaClass.classLoader.getResourceAsStream(path)?.use { inputStream ->
            inputStream.readBytes()
        } ?: error("PDF resource not found on classpath: $path")
    }


    override suspend fun load(path: String): ByteArray {
        val bytes = loadBytes(path)
        return bytes
    }

}