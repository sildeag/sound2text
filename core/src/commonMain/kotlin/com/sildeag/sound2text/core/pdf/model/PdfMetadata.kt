package com.sildeag.sound2text.core.pdf.model

import kotlinx.serialization.Serializable

@Serializable
data class PdfMetadata(
    val title: String? = null,
    val author: String? = null,
    val creationDate: String? = null,
    val modificationDate: String? = null,
    val subject: String? = null,
    val creator: String? = null,
    val pageCount: Int = 0,
    val isEncrypted: Boolean = false
) {
}