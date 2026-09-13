package com.sildeag.sound2text.core.stt.model

interface SttModelInfo {
    val engineName: String
    val language: String?
    val path: String
}

/*
package com.sildeag.sound2text.core.stt.model

interface SttModelInfo {
    val engineName: String        // "vosk", "whisper", "unified"
    val language: String?         // "en", "es", etc.
    val path: String              // absolute or relative model path
}
*/