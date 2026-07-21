package com.sildeag.sound2text.core.notes

import kotlinx.coroutines.flow.Flow
interface NoteRepository {
    fun getAllNotes(): Flow<List<Note>>
    suspend fun save(note: Note)
    suspend fun delete(note: Note)
}
