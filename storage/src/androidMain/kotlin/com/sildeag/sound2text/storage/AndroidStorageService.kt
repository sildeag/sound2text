package com.sildeag.sound2text.storage

import android.content.Context
import com.sildeag.sound2text.core.model.note.Note
import com.sildeag.sound2text.core.model.note.NoteJson
import com.sildeag.sound2text.storage.StorageService
import com.sildeag.sound2text.storage.StorageResult
import com.sildeag.sound2text.storage.StorageError
import java.io.File

class AndroidStorageService(
    private val context: Context
) : StorageService {
    private fun resolve(path: String): File =
        File(context.filesDir, path)
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
        try {
            val file = resolve("notes.json")
            file.parentFile?.mkdirs()
            val json = NoteJson.encodeList(notes)
            file.writeText(json)
        } catch (_: Exception) { }
    }
    override fun loadNotes(): List<Note> {
        return try {
            val file = resolve("notes.json")
            if (!file.exists()) return emptyList()
            val json = file.readText()
            NoteJson.decodeList(json)
        } catch (_: Exception) {
            emptyList()
        }
    }
    override fun saveText(name: String, value: String) {
        try {
            val file = resolve("$name.txt")
            file.parentFile?.mkdirs()
            file.writeText(value)
        } catch (_: Exception) { }
    }
}