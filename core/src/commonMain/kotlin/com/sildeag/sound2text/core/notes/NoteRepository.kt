package com.sildeag.sound2text.core.notes

import com.sildeag.sound2text.core.model.note.Note
import kotlinx.coroutines.flow.Flow
interface NoteRepository {
    fun getAllNotes(): Flow<List<Note>>
    suspend fun save(note: Note)
    suspend fun delete(note: Note)
}
