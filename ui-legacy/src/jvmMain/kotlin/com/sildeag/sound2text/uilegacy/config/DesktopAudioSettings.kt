package com.sildeag.sound2text.uilegacy.config

import kotlinx.serialization.Serializable

@Serializable
data class DesktopAudioSettings(
    val sampleRate: Float = 16000f,
    val bitDepth: Int = 16,
    val channels: Int = 1,
    val signed: Boolean = true,
    val bigEndian: Boolean = false,
    val javaSoundBufferSize: Int = 4096
)
