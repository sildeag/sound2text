package com.sildeag.sound2text.testharness

object TestAudioLoader {
    fun loadWavResource(path: String): ByteArray {
        val stream =
            this::class.java.classLoader.getResourceAsStream(path)
                ?: error("Test audio not found: $path")
        return stream.readBytes()
    }
}
