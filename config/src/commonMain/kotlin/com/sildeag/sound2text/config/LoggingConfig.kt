package com.sildeag.sound2text.config

import kotlinx.serialization.Serializable

@Serializable
sealed interface LoggingConfig {
    @Serializable
    data object Default : LoggingConfig
    @Serializable
    data class File(val path: String) : LoggingConfig
}