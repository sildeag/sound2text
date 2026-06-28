package com.sildeag.sound2text.core.stt

import kotlinx.serialization.Serializable
@Serializable
data class ModelDescriptor(
    val id: String,
    val name: String,
    val language: String,
    val path: String? = null
)
