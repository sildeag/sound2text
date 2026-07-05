package pdf

import com.sildeag.sound2text.core.pdf.PdfFieldType

data class PdfFieldDescriptor(
    val name: String,
    val type: PdfFieldType,
    val value: String?,
    val language: String?,
    val voiceEnabled: Boolean
)