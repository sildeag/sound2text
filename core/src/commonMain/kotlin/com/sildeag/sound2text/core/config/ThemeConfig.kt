package com.sildeag.sound2text.core.config

import kotlinx.serialization.Serializable

@Serializable
sealed interface ThemeConfig {
    @Serializable
    data object Default : ThemeConfig
    @Serializable
    data class Custom(val primary: String, val accent: String) :
        ThemeConfig
}
