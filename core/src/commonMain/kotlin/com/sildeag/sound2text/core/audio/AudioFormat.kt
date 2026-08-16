package com.sildeag.sound2text.core.audio

data class AudioFormat(
    val sampleRate: Int = 16000,
    val channels: Int = 1,
    val encoding: Encoding = Encoding.PCM_16BIT
) {
    enum class Encoding { PCM_16BIT, PCM_FLOAT }
}
