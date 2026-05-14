package com.sildeag.sound2text.core.stt.vosk

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttEngineDescriptor
import com.sildeag.sound2text.core.stt.SttService

object VoskDesktopEngine : SttEngineDescriptor {
    override val id = "vosk-desktop"
    override val displayName = "Vosk (Desktop)"
    override fun create(config: SttConfig): SttService =
        VoskSttService(model = DesktopModelProvider.get(), config = config)
}