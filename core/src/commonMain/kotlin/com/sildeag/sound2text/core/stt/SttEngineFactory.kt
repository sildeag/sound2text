package com.sildeag.sound2text.core.stt

interface SttEngineFactory {
    fun load(config: SttConfig): SttService
}
