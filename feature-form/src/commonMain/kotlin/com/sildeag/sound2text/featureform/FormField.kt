package com.sildeag.sound2text.featureform

data class FormField(
    val id: String,
    val pageIndex: Int,
    val label: String,
    val value: String = ""
)