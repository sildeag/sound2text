package com.sildeag.sound2text.sttdesktop.service

import com.sildeag.sound2text.core.stt.SttConfig

actual class SttService actual constructor() {
    private val engine = vosk.DesktopVoskService()
    actual suspend fun startRecognition(config: SttConfig) =
        engine.startRecognition(config)
    actual suspend fun stop() =
        engine.stop()
}
