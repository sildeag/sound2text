package com.sildeag.sound2text.core.stt

interface SttModelInfo {
    val language: String
    val sttEngine: String
    val modelPath: String?
    val modelFile: String?
    val androidModelDir: String?
    val androidModelFile: String?
}