package com.sildeag.sound2text.core.storage

interface FileStorageService {
    /**
     * Save text content to a file.
     * @param path relative or absolute path
     * @param content text to write
     */
    suspend fun save(path: String, content: String)

    /**
     * Load text content from a file.
     * @param path relative or absolute path
     * @return file contents or null if missing
     */
    suspend fun load(path: String): String?
}
