package com.sildeag.sound2text.androidui.storage

import android.content.// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
import com.sildeag.sound2text.model.Note
class AndroidStorageService(
    private val // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
) : StorageService {
    override fun saveNotes(notes: List<Note>) {
        println("AndroidStorageService.saveNotes called (stub)")
    }
    override fun loadNotes(): List<Note> {
        println("AndroidStorageService.loadNotes called (stub)")
        return emptyList()
    }
}
