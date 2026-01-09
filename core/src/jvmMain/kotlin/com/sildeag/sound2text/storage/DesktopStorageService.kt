package com.sildeag.sound2text.storage

import com.sildeag.sound2text.service.note.Note


class DesktopStorageService : StorageService {

    override fun saveNotes(notes: List<Note>) {
        println("DesktopStorageService.saveNotes called (stub)")
    }
    override fun loadNotes(): List<Note> {
        println("DesktopStorageService.loadNotes called (stub)")
        return emptyList()
    }
}

