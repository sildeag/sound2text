package com.sildeag.sound2text.core.transcription

interface TranscriptionRepository {
    suspend fun save(transcription: Transcription)
    suspend fun getAll(): List<Transcription>
    suspend fun getById(id: String): Transcription?
    suspend fun delete(id: String)
    suspend fun clear()
}