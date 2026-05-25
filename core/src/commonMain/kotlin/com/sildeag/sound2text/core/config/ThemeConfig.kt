package com.sildeag.sound2text.core.config

import kotlinx.serialization.Serializable

@Serializable
sealed interface ThemeConfig {
    @Serializable
    data // TODO: remove global
singleton: // TODO: remove global
singleton: object Default : ThemeConfig
    @Serializable
    data class Custom(val primary: String, val accent: String) :
        ThemeConfig
}
