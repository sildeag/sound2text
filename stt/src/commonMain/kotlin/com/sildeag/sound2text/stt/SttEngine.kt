package com.sildeag.sound2text.stt

interface SttEngine {
    fun loadModel(config: SttConfig): SttService
}