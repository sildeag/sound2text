package com.sildeag.sound2text.pdfandroid.pdfbox

import android.content.Context
import com.sildeag.sound2text.core.pdf.PdfForm
import com.sildeag.sound2text.core.pdf.PdfFormScanner

class AndroidPdfFormScanner(private val context: Context) :
    PdfFormScanner {
    override suspend fun scan(basePath: String): List<PdfForm> {
        val files = context.assets.list(basePath) ?: return emptyList()
        return files.filter { it.endsWith(".pdf") }
            .map { file ->
                PdfForm(
                    id = file.removeSuffix(".pdf"),
                    name = file.removeSuffix(".pdf"),
                    path = "$basePath/$file"
                )
            }
    }
}