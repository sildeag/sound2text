package com.sildeag.sound2text.core.audio

expect interface SoundSource {
    val id: String // could be a path, URI, or anything platform-specific
    // or, if you want the player to receive raw audio data instead of a file reference:
    // suspend fun readBytes(): ByteArray
}
