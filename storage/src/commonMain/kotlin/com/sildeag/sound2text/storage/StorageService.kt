package com.sildeag.sound2text.storage

import com.sildeag.sound2text.core.model.note.Note

interface StorageService {
    fun saveNotes(notes: List<Note>)
    fun loadNotes(): List<Note>
    fun saveText(name: String, value: String)
}