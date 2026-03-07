package com.sildeag.sound2text.config

interface SttEngineFactory {
    fun create(settings: SpeechToTextSettings): SttEngine
}