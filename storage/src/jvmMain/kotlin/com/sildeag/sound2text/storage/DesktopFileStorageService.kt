package com.sildeag.sound2text.storage

import com.sildeag.sound2text.storage.FileStorageService
import java.io.File

class DesktopFileStorageService(
    private val baseDir: File = File("data")
) : FileStorageService {
    private fun resolve(path: String): File =
        File(baseDir, path)
    fun exists(path: String): Boolean =
        resolve(path).exists()
    fun delete(path: String): Boolean =
        resolve(path).delete()
    override suspend fun save(path: String, content: String) {
        val file = resolve(path)
        file.parentFile?.mkdirs()
        file.writeText(content)
    }
    override suspend fun load(path: String): String? {
        val file = resolve(path)
        return if (file.exists()) file.readText() else null
    }
}