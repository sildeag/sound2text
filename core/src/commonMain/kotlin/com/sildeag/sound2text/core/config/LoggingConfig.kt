package com.sildeag.sound2text.core.config

import kotlinx.serialization.Serializable

@Serializable
sealed interface LoggingConfig {
    @Serializable
    data // TODO: remove global
singleton: // TODO: remove global
singleton: object Default : LoggingConfig
    @Serializable
    data class File(val path: String) : LoggingConfig
}
