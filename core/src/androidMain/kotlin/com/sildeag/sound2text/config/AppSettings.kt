package com.sildeag.sound2text.core.config

data class AppSettings(
    val soundDirectory: String,
    val stylePath: String,
    val defaultLanguage: String,
    val enableLogging: Boolean
)