package com.sildeag.sound2text.audio

import org.vosk.Recognizer
import javax.sound.sampled.AudioFormat

interface RecognizerProvider {
    fun create(): Recognizer?
    fun getAudioFormat(): AudioFormat
}
