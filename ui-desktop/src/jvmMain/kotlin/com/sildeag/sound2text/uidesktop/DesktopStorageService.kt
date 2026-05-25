package com.sildeag.sound2text.uidesktop

import com.sildeag.sound2text.pdfdesktop.AppSettings
import com.sildeag.sound2text.core.model.note.Note
class DesktopStorageService(settings: AppSettings) : StorageService {
    override fun saveNotes(notes: List<Note>) {
        println("DesktopStorageService.saveNotes called (stub)")
    }
    override fun loadNotes(): List<Note> {
        println("DesktopStorageService.loadNotes called (stub)")
        return emptyList()
    }
}
