package com.sildeag.sound2text.core.service.recognizer

class MockRecognizer : Recognizer {
    override fun start(onTextRecognized: (String) -> Unit) {
        onTextRecognized("This is a mock transcription.")
    }
    override fun stop() {}
}
