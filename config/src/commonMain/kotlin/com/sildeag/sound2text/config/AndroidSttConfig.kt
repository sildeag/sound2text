package com.sildeag.sound2text.config

import kotlinx.serialization.Serializable

@Serializable
data class AndroidSttConfig(
    val language: String
) : SttEngineConfig