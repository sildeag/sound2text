package com.sildeag.sound2text.androidui.storage

import android.content.Context
import com.sildeag.sound2text.model.Note
class AndroidStorageService(
    private val context: Context
) : StorageService {
    override fun saveNotes(notes: List<Note>) {
        println("AndroidStorageService.saveNotes called (stub)")
    }
    override fun loadNotes(): List<Note> {
        println("AndroidStorageService.loadNotes called (stub)")
        return emptyList()
    }
}
