package com.sildeag.sound2text.pdfdesktop

import com.sildeag.sound2text.core.pdf.PdfForm
import com.sildeag.sound2text.core.pdf.PdfFormScanner
import java.io.File

class DesktopPdfFormScanner : PdfFormScanner {
    override suspend fun scan(basePath: String): List<PdfForm> {
        val url = javaClass.classLoader.getResource(basePath)
            ?: return emptyList()
        val dir = File(url.toURI())
        return dir.listFiles { f -> f.extension == "pdf" }
            ?.map { file ->
                PdfForm(
                    id = file.nameWithoutExtension,
                    name = file.nameWithoutExtension,
                    path = "$basePath/${file.name}"
                )
            }
            ?: emptyList()
    }
}
