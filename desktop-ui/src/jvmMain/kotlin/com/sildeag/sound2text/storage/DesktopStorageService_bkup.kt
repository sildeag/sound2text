/*
package com.sildeag.sound2text.storage


import com.sildeag.sound2text.viewmodel.Note
import java.io.File

class DesktopStorageService(
    private val baseDir: File = File("notes")
) : StorageService {
    init {
        if (!baseDir.exists()) {
            baseDir.mkdirs()
        }
    }
    override fun saveNotes(note: Note) {
        val file = File(baseDir, "${note.id}.json")
        file.writeText(note.toJson()) // implement toJson() in Note
    }
    override fun loadNotes(id: String): Note? {
        val file = File(baseDir, "$id.json")
        return if (file.exists()) {
            Note.fromJson(file.readText()) // implement fromJson() in
            Note
        } else null
    }
    override fun listNotes(): List<Note> {
        return baseDir.listFiles { f -> f.extension == "json" }
            ?.map { Note.fromJson(it.readText()) }
            ?: emptyList()
    }

    override fun saveNotes(notes: List<Note>) {
        TODO("Not yet implemented")
    }

    override fun loadNotes(): List<Note> {
        TODO("Not yet implemented")
    }


}
*/