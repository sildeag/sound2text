package com.sildeag.sound2text.core.config

interface DefaultConfig {
    val language: String
    val sampleRate: Int
}
class DefaultConfigImpl : DefaultConfig {
    override val language = "en"
    override val sampleRate = 16000
}