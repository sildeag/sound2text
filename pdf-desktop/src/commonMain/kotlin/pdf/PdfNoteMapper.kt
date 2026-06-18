package pdf

import com.sildeag.sound2text.core.model.note.*
class NotePdfMapper {
    fun mapToPdfFields(note: Note): List<PdfFieldDescriptor> {
        return note.fields.mapNotNull { field ->
            when (field) {
                is NotePdfField -> PdfFieldDescriptor(
                    name = field.pdfFieldName,
                    type = PdfFieldType.TEXT,
                    value = field.value,
                    language = field.language,
                    voiceEnabled = false
                )

                is NoteTextField -> PdfFieldDescriptor(
                    name = field.anchor ?: field.id,
                    type = PdfFieldType.TEXT,
                    value = field.text,
                    language = field.language,
                    voiceEnabled = field.voiceEnabled
                )

                else -> null
            }
        }
    }
}
