package com.sildeag.sound2text.core.notes

import androidx.sqlite.SQLiteConnection
import androidx.sqlite.SQLiteStatement
import com.sildeag.sound2text.core.model.note.*
import com.sildeag.sound2text.core.serialization.NoteFieldJson
import com.sildeag.sound2text.core.serialization.NoteFieldPayload
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NoteRepositorySQLite(
    private val connection: SQLiteConnection
) : NoteRepository {
    override fun getAllNotes(): Flow<List<Note>> = flow {
        emit(loadNotes())
    }

    private fun loadNotes(): List<Note> {
        val notes = mutableListOf<Note>()
        connection.prepare("SELECT id, title FROM notes").use { stmt ->
            while (stmt.step()) {
                val id = stmt.getText(0)
                val title = stmt.getText(1)
                val fields = loadFields(id)
                notes += Note(id, title, fields)
            }
        }
        return notes
    }

    private fun loadFields(noteId: String): List<NoteField> {
        val fields = mutableListOf<NoteField>()
        connection.prepare("SELECT id, type, level, anchor, language, payload FROM note_fields WHERE note_id = ?").use { stmt ->
            stmt.bindText(1, noteId)
            while (stmt.step()) {
                val id = stmt.getText(0)
                val type = stmt.getText(1)
                val level = stmt.getLong(2).toInt()
                val anchor = stmt.getText(3)
                val language = stmt.getText(4)
                val payloadRaw = stmt.getText(5)
                
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
        }
        return fields
    }

    override suspend fun save(note: Note) {
        connection.prepare("INSERT OR REPLACE INTO notes (id, title) VALUES (?, ?)").use { stmt ->
            stmt.bindText(1, note.id)
            stmt.bindText(2, note.title.toString())
            stmt.step()
        }

        connection.prepare("DELETE FROM note_fields WHERE note_id = ?").use { stmt ->
            stmt.bindText(1, note.id)
            stmt.step()
        }

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

            connection.prepare("""
                INSERT INTO note_fields (id, note_id, type, level, anchor, language, payload)
                VALUES (?, ?, ?, ?, ?, ?, ?)
            """.trimIndent()).use { stmt ->
                stmt.bindText(1, field.id)
                stmt.bindText(2, note.id)
                stmt.bindText(3, type)
                stmt.bindInt(4, field.level.toLong())
                stmt.bindText(5, field.anchor ?: "")
                stmt.bindText(6, field.language)
                stmt.bindText(7, NoteFieldJson.encode(payload))
                stmt.step()
            }
        }
    }

    override suspend fun delete(note: Note) {
        connection.prepare("DELETE FROM notes WHERE id = ?").use { stmt ->
            stmt.bindText(1, note.id)
            stmt.step()
        }
    }
}
