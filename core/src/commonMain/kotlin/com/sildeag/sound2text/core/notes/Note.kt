package com.sildeag.sound2text.core.notes

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class Note(
    val id: String,
    val title: String,
    val fields: List<NoteField>,
    val updatedAt: Instant = Clock.System.now()
) {
    val updatedAtFormatted: String
        get() {
            val local = updatedAt.toLocalDateTime(TimeZone.currentSystemDefault())
            return "${local.year}-${local.monthNumber.toString().padStart(2, '0')}-${local.dayOfMonth.toString().padStart(2, '0')} " +
                    "${local.hour.toString().padStart(2, '0')}:${local.minute.toString().padStart(2, '0')}"
        }

    val previewText: String =
        fields.filterIsInstance<NoteTextField>().firstOrNull()?.text ?: ""
}
