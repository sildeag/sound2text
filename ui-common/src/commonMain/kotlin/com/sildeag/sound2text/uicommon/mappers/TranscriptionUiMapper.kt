package com.sildeag.sound2text.uicommon.mappers

import com.sildeag.sound2text.core.transcription.Transcription
import com.sildeag.sound2text.uicommon.models.UiTranscript
import com.sildeag.sound2text.uicommon.models.TranscriptSource
/**
 * Maps core Transcript into UiTranscript for display.
 */
class TranscriptUiMapper {
    fun map(core: Transcription): UiTranscript =
        UiTranscript(
            id = core.id,
            text = core.text,
            createdAt = core.createdAt,
            durationMs = core.durationMs,
            source = when (core.source) {
                "mic" -> TranscriptSource.Microphone
                "file" -> TranscriptSource.FileImport
                "pdf" -> TranscriptSource.PdfCommentary
                else -> TranscriptSource.Microphone
            }
        )
}