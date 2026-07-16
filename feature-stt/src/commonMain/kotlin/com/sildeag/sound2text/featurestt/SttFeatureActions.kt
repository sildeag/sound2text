package com.sildeag.sound2text.featurestt

sealed interface SttFeatureActions {
    data class TranscribeAudio(val bytes: ByteArray) : SttFeatureActions
}