package com.sildeag.sound2text.sttdesktop.jvm

import com.sildeag.sound2text.sttdesktop.SttConfig

actual class SttService actual constructor() {
    private val engine = vosk.DesktopVoskService()
    actual suspend fun startRecognition(config: SttConfig) =
        engine.startRecognition(config)
    actual suspend fun stop() =
        engine.stop()
}
