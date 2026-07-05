package com.sildeag.sound2text.storage

import com.sildeag.sound2text.core.model.note.Note
import com.sildeag.sound2text.core.model.note.NoteJson
import com.sildeag.sound2text.storage.StorageResult
import com.sildeag.sound2text.storage.StorageError
import java.io.File

class DesktopStorageService(
    private val baseDir: File = File("data")
) : StorageService {
    private fun resolve(path: String): File =
        File(baseDir, path)
    fun save(path: String, bytes: ByteArray): StorageResult<Unit> {
        return try {
            val file = resolve(path)
            file.parentFile?.mkdirs()
            file.writeBytes(bytes)
            StorageResult.Success(Unit)
        } catch (e: Exception) {
            StorageResult.Failure(StorageError.IOError(e.message ?: "Unknown error"))
        }
    }
    fun load(path: String): StorageResult<ByteArray> {
        return try {
            val file = resolve(path)
            if (!file.exists()) return StorageResult.Failure(StorageError.NotFound)
            StorageResult.Success(file.readBytes())
        } catch (e: Exception) {
            StorageResult.Failure(StorageError.IOError(e.message ?: "Unknown error"))
        }
    }

    override fun saveNotes(notes: List<Note>) {
        return try {
            val file = resolve("notes.json")
            file.parentFile?.mkdirs()
            val json = NoteJson.encodeList(notes) // your serializer
            file.writeText(json)
        } catch (e: Exception) {
            // You may want to log this later
        }
    }
    override fun loadNotes(): List<Note> {
        return try {
            val file = resolve("notes.json")
            if (!file.exists()) return emptyList()
            val json = file.readText()
            NoteJson.decodeList(json) // your serializer
        } catch (e: Exception) {
            emptyList()
        }
    }
    override fun saveText(name: String, value: String) {
        return try {
            val file = resolve("$name.txt")
            file.parentFile?.mkdirs()
            file.writeText(value)
        } catch (e: Exception) {
            // optional logging
        }
    }

}
