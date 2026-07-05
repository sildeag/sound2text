package com.sildeag.sound2text.storage

import android.content.Context
import com.sildeag.sound2text.storage.FileStorageService
import java.io.File

class AndroidFileStorageService(
    private val context: Context
) : FileStorageService {
    private fun resolve(path: String): File =
        File(context.filesDir, path)
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