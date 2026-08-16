package com.sildeag.sound2text.core.stt.engine

import com.sildeag.sound2text.core.stt.config.SttConfig

interface SttEngineFactory {
    fun load(config: SttConfig): SttEngine
}