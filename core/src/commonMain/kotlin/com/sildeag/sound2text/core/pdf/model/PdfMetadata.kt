package com.sildeag.sound2text.core.pdf.model

import kotlinx.datetime.Instant
data class PdfMetadata(
    val title: String?,
    val author: String?,
    val creationDate: String?,
    val modificationDate: String?
)