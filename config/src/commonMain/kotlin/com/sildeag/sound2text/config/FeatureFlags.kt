package com.sildeag.sound2text.config

data class FeatureFlags(
    val enableStt: Boolean = true,
    val enableNoiseSuppression: Boolean = false,
    val enableDebugLogging: Boolean = false
)
