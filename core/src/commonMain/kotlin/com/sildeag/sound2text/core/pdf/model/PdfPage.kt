package com.sildeag.sound2text.core.pdf.model

import kotlinx.serialization.Serializable

@Serializable
data class PdfPage(
    val index: Int,
    val pageNumber: Int,
    val text: String
 ) {

}
