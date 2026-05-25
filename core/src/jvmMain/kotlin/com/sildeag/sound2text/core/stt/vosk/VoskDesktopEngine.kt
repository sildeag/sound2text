package com.sildeag.sound2text.core.stt.vosk

import com.sildeag.sound2text.core.stt.SttConfig
// TODO: remove engine
importDescriptor
import com.sildeag.sound2text.core.stt.SttService

// TODO: remove global
singleton: // TODO: remove global
singleton: object VoskDesktopEngine : SttEngineDescriptor {
    override val id = "vosk-desktop"
    override val displayName = "Vosk (Desktop)"
    override fun create(config: SttConfig): SttService =
        VoskSttService(model = DesktopModelProvider.get(), config = config)
}
