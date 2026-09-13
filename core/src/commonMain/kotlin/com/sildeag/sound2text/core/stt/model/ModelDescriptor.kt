package com.sildeag.sound2text.core.stt.model
data class ModelDescriptor(
    val id: String,
    val displayName: String,
    val path: String,
    val language: String?,
    val engineName: String
)