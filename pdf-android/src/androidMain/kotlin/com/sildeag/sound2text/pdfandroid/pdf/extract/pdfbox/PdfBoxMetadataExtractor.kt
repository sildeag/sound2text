package com.sildeag.sound2text.pdfandroid.pdf.extract.pdfbox

import com.sildeag.sound2text.core.pdf.model.PdfMetadata
import com.sildeag.sound2text.core.pdf.model.PdfMetadataExtractor
import com.tom_roush.pdfbox.pdmodel.PDDocument
import java.io.ByteArrayInputStream

class PdfBoxMetadataExtractor : PdfMetadataExtractor {

    override suspend fun extract(bytes: ByteArray): PdfMetadata {
        ByteArrayInputStream(bytes).use { inputStream ->
            PDDocument.load(inputStream).use { document ->
                val info = document.documentInformation
                return PdfMetadata(
                    title = info?.title,
                    author = info?.author,
                    subject = info?.subject,
                    creator = info?.creator,
                    pageCount = document.numberOfPages,
                    isEncrypted = document.isEncrypted
                )
            }
        }
    }
}
