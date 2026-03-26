package com.sildeag.sound2text.core.config

data class FeatureFlags(
    val enableStt: Boolean = true,
    val enableNoiseSuppression: Boolean = false,
    val enableDebugLogging: Boolean = false
)
