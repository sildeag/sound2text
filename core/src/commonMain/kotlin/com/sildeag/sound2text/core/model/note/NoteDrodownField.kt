package com.sildeag.sound2text.core.model.note

data class NoteDropdownField(
    override val id: String,
    override val anchor: String?,
    override val level: Int,
    override val language: String?,
    val label: String,
    val options: List<String>,
    val selected: String?
) : NoteField
