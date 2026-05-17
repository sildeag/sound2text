package com.sildeag.sound2text.core.model.note

data class NotePdfField(
    override val id: String,
    override val anchor: String?,
    override val level: Int,
    override val language: String?,
    val pdfFieldName: String,
    val value: String
) : NoteField
