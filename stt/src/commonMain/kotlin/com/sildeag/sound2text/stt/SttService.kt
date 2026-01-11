package com.sildeag.sound2text.stt

expect interface SttService {
    fun transcribe(audio: ByteArray): SttResult
}
