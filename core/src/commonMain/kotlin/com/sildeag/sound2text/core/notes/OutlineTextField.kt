package com.sildeag.sound2text.core.notes

data class OutlineFieldText(
    val id: String,
    val text: String,
    val level: Int,
    val anchor: String? = null,
    val language: String? = null
)
