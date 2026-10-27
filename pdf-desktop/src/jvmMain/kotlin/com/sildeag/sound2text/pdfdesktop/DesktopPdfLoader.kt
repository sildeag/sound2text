package com.sildeag.sound2text.pdfdesktop

import com.itextpdf.kernel.pdf.PdfReader
import com.itextpdf.kernel.pdf.PdfDocument
import com.sildeag.sound2text.core.pdf.api.LoadedPdf
import com.sildeag.sound2text.core.pdf.api.PdfLoader
import com.sildeag.sound2text.core.pdf.model.PdfPage

class DesktopPdfLoader(
    private val metadataExtractor:
    DesktopPdfMetadataExtractor
) : PdfLoader {
    override suspend fun load(bytes: ByteArray): LoadedPdf
    {
        val reader = PdfReader(bytes.inputStream())
        val pdf = PdfDocument(reader)
        val pages = (1..pdf.numberOfPages).map { index ->
            val page = pdf.getPage(index)
            PdfPage(
                index = index - 1,
                width = page.pageSize.width.toInt(),
                height = page.pageSize.height.toInt(),
                text = "text from page is currently unassigned"
            )
        }
        val metadata = metadataExtractor.extract(pdf)
        return LoadedPdf(pages, metadata)
    }
}