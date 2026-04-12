package com.sildeag.sound2text.service.recognizer

interface Recognizer {
    fun start(onTextRecognized: (String) -> Unit)

    fun stop()
}
