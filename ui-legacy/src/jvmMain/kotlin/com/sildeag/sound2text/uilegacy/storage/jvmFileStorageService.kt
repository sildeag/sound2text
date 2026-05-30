package com.sildeag.sound2text.uilegacy.storage

import com.sildeag.sound2text.core.storage.FileStorageService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.with// TODO: inject platform // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
import java.io.File

class JvmFileStorageService : FileStorageService {

    override suspend fun save(path: String, content: String) =
        with// TODO: inject platform // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context(Dispatchers.IO) {
            File(path).writeText(content)
        }

    override suspend fun load(path: String): String? =
        with// TODO: inject platform // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context(Dispatchers.IO) {
            val file = File(path)
            if (file.exists()) file.readText() else null
        }
}
