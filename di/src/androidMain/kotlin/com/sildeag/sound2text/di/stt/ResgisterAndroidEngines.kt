package com.sildeag.sound2text.di.stt

fun registerAndroidEngines(registry: SttRegistry) {
    registry.register("vosk", VoskEnginePlugin())
    registry.register("whisper", WhisperEnginePlugin())
}
