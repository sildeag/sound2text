package com.sildeag.sound2text.core.pdf

data class PdfFormDescriptor(
    val engine: String,
    val formName: String,
    val path: String,
    val fields: List<PdfFieldDescriptor>
)
data class PdfFieldDescriptor(
    val name: String,
    val type: FieldType,
    val voiceEnabled: Boolean,
    val language: String? = null
)
enum class FieldType {
    TEXT,
    CHECKBOX,
    RADIO,
    DROPDOWN,
    SIGNATURE,
    DATE,
    UNKNOWN
}
