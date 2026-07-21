package com.sildeag.sound2text.core.notes

data class NoteCheckboxField(
    override val id: String,
    override val anchor: String?,
    override val level: Int,
    override val language: String?,
    val label: String,
    val checked: Boolean
) : NoteField
