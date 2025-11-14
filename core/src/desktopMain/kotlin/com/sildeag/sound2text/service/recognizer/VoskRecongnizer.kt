package com.sildeag.sound2text.core.service.recognizer

interface Recognizer {
    fun start(onTextRecognized: (String) -> Unit)

    fun stop()
}
