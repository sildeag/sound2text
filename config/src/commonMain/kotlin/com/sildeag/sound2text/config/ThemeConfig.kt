package com.sildeag.sound2text.config

import kotlinx.serialization.Serializable

@Serializable
sealed interface ThemeConfig {
    @Serializable
    data object Default : ThemeConfig
    @Serializable
    data class Custom(val primary: String, val accent: String) :
        ThemeConfig
}
