package com.sildeag.sound2text.core.config

interface DefaultConfig {
    val language: String
    val sampleRate: Int
}
expect class DefaultConfigImpl() : DefaultConfig