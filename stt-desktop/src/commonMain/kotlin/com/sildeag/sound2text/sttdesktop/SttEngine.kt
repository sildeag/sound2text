package com.sildeag.sound2text.sttdesktop

interface SttEngine {
    fun loadModel(config: SttConfig): SttService
}