package com.sildeag.sound2text.sttdesktop

expect interface SttService {
    fun transcribe(audio: ByteArray): SttResult
}
