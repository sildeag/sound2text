package com.sildeag.sound2text.service.recognizer

import org.vosk.Recognizer

class FakeRecognizer : Recognizer(null, 16000.0f) {
    private var called = false
    override fun acceptWaveForm(data: ByteArray?, len: Int): Boolean {
        return true // Always accept
    }
    override fun getResult(): String {
        if (!called) {
            called = true
            return """{ "text": "hello world" }"""
        }
        return """{ "text": "" }"""
    }
}