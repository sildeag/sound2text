package com.sildeag.sound2text.core.model.note

interface NoteEditor {
    fun insertField(field: NoteField)
    fun updateField(field: NoteField)
    fun removeField(id: String)
    fun getFields(): List<NoteField>
    fun grammarCheck(fieldId: String): String
}
