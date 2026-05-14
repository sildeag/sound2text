package com.sildeag.sound2text.core.notes

import com.sildeag.sound2text.core.model.note.*
import com.sildeag.sound2text.core.serialization.NoteFieldJson
import com.sildeag.sound2text.core.serialization.NoteFieldPayload
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
class NoteRepositorySQLite(
    private val db: SQLiteDriver
) : NoteRepository {
    override fun getAllNotes(): Flow<List<Note>> = flow {
        emit(loadNotes())
    }
    private fun loadNotes(): List<Note> {
        val notes = mutableListOf<Note>()
        val cursor = db.executeQuery("SELECT id, title FROM notes")
        while (cursor.next()) {
            val id = cursor.getString(0)
            val title = cursor.getString(1)
            val fields = loadFields(id)
            notes += Note(id, title, fields)
        }
        cursor.close()
        return notes
    }
    private fun loadFields(noteId: String): List<NoteField> {
        val fields = mutableListOf<NoteField>()
        val cursor = db.executeQuery(
            "SELECT id, type, level, anchor, language, payload FROM
                    note_fields WHERE note_id = ?",
        listOf(noteId)
        )
        while (cursor.next()) {
            val id = cursor.getString(0)
            val type = cursor.getString(1)
            val level = cursor.getInt(2)
            val anchor = cursor.getString(3)
            val language = cursor.getString(4)
            val payloadRaw = cursor.getString(5)
            val payload = NoteFieldJson.decode(type, payloadRaw)
            val field = when (payload) {
                is NoteFieldPayload.Text ->
                    NoteTextField(id, anchor, level, language,
                        payload.text, payload.translations, payload.voiceEnabled)
                is NoteFieldPayload.Checkbox ->
                    NoteCheckboxField(id, anchor, level, language,
                        payload.label, payload.checked)
                is NoteFieldPayload.Dropdown ->
                    NoteDropdownField(id, anchor, level, language,
                        payload.label, payload.options, payload.selected)
                is NoteFieldPayload.Pdf ->
                    NotePdfField(id, anchor, level, language,
                        payload.pdfFieldName, payload.value)
            }
            fields += field
        }
        cursor.close()
        return fields
    }
    override suspend fun save(note: Note) {
        db.execute("INSERT OR REPLACE INTO notes (id, title) VALUES
            (?, ?)", listOf(note.id, note.title))
        db.execute("DELETE FROM note_fields WHERE note_id = ?",
            listOf(note.id))
        note.fields.forEach { field ->
            val (type, payload) = when (field) {
                is NoteTextField -> "text" to
                        NoteFieldPayload.Text(field.text, field.translations,
                            field.voiceEnabled)
                is NoteCheckboxField -> "checkbox" to
                        NoteFieldPayload.Checkbox(field.label, field.checked)
                is NoteDropdownField -> "dropdown" to
                        NoteFieldPayload.Dropdown(field.label, field.options, field.selected)
                is NotePdfField -> "pdf" to
                        NoteFieldPayload.Pdf(field.pdfFieldName, field.value)
            }
            db.execute(
                """
 INSERT INTO note_fields (id, note_id, type, level,
anchor, language, payload)
 VALUES (?, ?, ?, ?, ?, ?, ?)
 """.trimIndent(),
                listOf(
                    field.id,
                    note.id,
                    type,
                    field.level,
                    field.anchor,
                    field.language,
                    NoteFieldJson.encode(payload)
                )
            )
        }
    }
    override suspend fun delete(note: Note) {
        db.execute("DELETE FROM notes WHERE id = ?", listOf(note.id))
    }
}