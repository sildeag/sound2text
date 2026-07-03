package com.sildeag.sound2text.storage

interface StorageProvider {
    fun read(path: String): ByteArray
    fun write(path: String, data: ByteArray)
}