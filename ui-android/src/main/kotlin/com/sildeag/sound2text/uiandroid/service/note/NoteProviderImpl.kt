package com.sildeag.sound2text.service.note

import android.content.Context
import com.sildeag.sound2text.core.model.note.NoteProvider

// Platform-specific implementation using SharedPreferences
class NoteProviderImpl(private val context: Context) : NoteProvider {
    private val prefs = context.getSharedPreferences("notes",
        Context.MODE_PRIVATE)
    override fun saveNote(id: String, text: String) {
        prefs.edit().putString(id, text).apply()
    }
    override fun loadNote(id: String): String? {
        return prefs.getString(id, null)
    }
}

