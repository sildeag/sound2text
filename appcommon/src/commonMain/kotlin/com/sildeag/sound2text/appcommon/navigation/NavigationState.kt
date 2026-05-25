package com.sildeag.sound2text.appcommon.navigation

import com.sildeag.sound2text.core.model.note.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
class NavigationState {
    sealed class Screen {
        data object NotesList : Screen()
        data class NoteDetail(val note: Note) : Screen()
        data class NoteEditor(val noteId: String?) : Screen()
        data object SoundToText : Screen()
    }

    private val _current = MutableStateFlow<Screen>(Screen.NotesList)
    val current: StateFlow<Screen> = _current
    fun openNotesList() {
        _current.value = Screen.NotesList
    }
    fun openNoteDetail(note: Note) {
        _current.value = Screen.NoteDetail(note)
    }
    fun openNoteDetail(noteId: String) {
        // Overload if you sometimes only have an ID and resolve elsewhere
        _current.value = Screen.NoteEditor(noteId) // or throw, or resolve via repo
    }
    fun openNoteEditor(noteId: String?) {
        _current.value = Screen.NoteEditor(noteId)
    }
    fun openSoundToText() {
        _current.value = Screen.SoundToText
    }
    fun goBack() {
        // Simple version: always go back to list
        _current.value = Screen.NotesList
    }
}
