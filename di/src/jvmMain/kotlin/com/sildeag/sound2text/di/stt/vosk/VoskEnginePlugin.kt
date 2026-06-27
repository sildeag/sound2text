package com.sildeag.sound2text.di.stt.vosk

import com.sildeag.sound2text.core.config.EngineConfig
import com.sildeag.sound2text.core.stt.SttEngine

class VoskEnginePlugin(
    private val engine: SttEngine,
    private val config: EngineConfig
)