package com.sildeag.sound2text.storage

interface StorageProvider {
    fun open(path: String): ByteArray
    fun save(path: String, data: ByteArray)
}