package com.sildeag.sound2text.desktop.storage

import com.sildeag.sound2text.core.storage.FileStorageService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class JvmFileStorageService : FileStorageService {

    override suspend fun save(path: String, content: String) =
        withContext(Dispatchers.IO) {
            File(path).writeText(content)
        }

    override suspend fun load(path: String): String? =
        withContext(Dispatchers.IO) {
            val file = File(path)
            if (file.exists()) file.readText() else null
        }
}