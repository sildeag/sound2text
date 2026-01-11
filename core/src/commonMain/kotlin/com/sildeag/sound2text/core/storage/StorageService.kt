package com.sildeag.sound2text.core.storage

/*
interface StorageService {
    fun save(key: String, value: String)
    fun load(key: String): String?
}

 */

import com.sildeag.sound2text.service.note.Note
interface StorageService {
    fun saveNotes(notes: List<Note>)
    fun loadNotes(): List<Note>
    fun saveText(name: String, value: String)
}