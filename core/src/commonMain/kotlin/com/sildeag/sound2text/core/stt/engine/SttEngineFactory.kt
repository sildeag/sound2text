package com.sildeag.sound2text.core.stt.engine

import com.sildeag.sound2text.core.stt.SttConfig

interface SttEngineFactory {
    fun load(config: SttConfig): SttEngine
}