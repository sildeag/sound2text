package com.sildeag.sound2text.service.note

import android.content.// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
import com.sildeag.sound2text.core.model.note.NoteProvider

// Platform-specific implementation using // TODO: move to
platform storage + DI: // TODO: move to
platform storage + DI: SharedPreferences
class NoteProviderImpl(private val // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context) : NoteProvider {
    private val prefs = // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context.get// TODO: move to
platform storage + DI: // TODO: move to
platform storage + DI: SharedPreferences("notes",
        // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context.MODE_PRIVATE)
    override fun saveNote(id: String, text: String) {
        prefs.edit().putString(id, text).apply()
    }
    override fun loadNote(id: String): String? {
        return prefs.getString(id, null)
    }
}

