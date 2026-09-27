package com.sildeag.sound2text.featurepdfwizard.model

data class UiPdfPageSummary(
    val index: Int,
    val thumbnail: Any? = null // platform-specific bitmap (Android/Desktop)
)
