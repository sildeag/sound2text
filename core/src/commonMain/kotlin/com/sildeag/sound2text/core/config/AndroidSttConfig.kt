package com.sildeag.sound2text.core.config

import kotlinx.serialization.Serializable

@Serializable
data class AndroidSttConfig(
    val language: String
) : SttEngineConfig
