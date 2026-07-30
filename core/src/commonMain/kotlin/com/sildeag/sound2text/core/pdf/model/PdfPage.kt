package com.sildeag.sound2text.core.pdf.model

import kotlinx.serialization.Serializable

@Serializable
data class PdfPage(
    val index: Int,
    val text: String
)
