package com.sildeag.sound2text.core.pdf.api

import com.sildeag.sound2text.core.pdf.model.PdfMetadata

interface PdfMetadataExtractor {
    fun extract(bytes: ByteArray): PdfMetadata
}