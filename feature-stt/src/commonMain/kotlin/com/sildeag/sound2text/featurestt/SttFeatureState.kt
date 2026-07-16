package com.sildeag.sound2text.featurestt

data class SttFeatureState(
    val transcript: String = "",
    val isProcessing: Boolean = false,
    val error: String? = null
)
