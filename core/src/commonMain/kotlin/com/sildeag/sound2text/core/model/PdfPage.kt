package com.sildeag.sound2text.core.model

import kotlinx.serialization.Serializable

@Serializable
data class PdfPage(
    val index: Int,
    val text: String
)