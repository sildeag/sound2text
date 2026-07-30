package com.sildeag.sound2text.uicommon.logic

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
class NotesRepository {
    private val _notes = MutableStateFlow(
        listOf(
            1L to "First Note",
            2L to "Second Note"
        )
    )
    val notes: StateFlow<List<Pair<Long, String>>> = _notes
    fun saveNote(id: Long?, title: String) {
        val newId = id ?: (_notes.value.maxOfOrNull { it.first } ?:
        0L) + 1L
        val updated = _notes.value.filterNot { it.first == newId } +
                (newId to title)
        _notes.value = updated.sortedBy { it.first }
    }
}