package com.sildeag.sound2text.core.config

interface SttEngineFactory {
    fun create(settings: SpeechToTextSettings): SttEngine
}