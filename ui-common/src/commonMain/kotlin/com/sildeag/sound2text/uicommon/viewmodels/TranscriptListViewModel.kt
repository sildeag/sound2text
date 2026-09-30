package com.sildeag.sound2text.uicommon.viewmodels

import com.sildeag.sound2text.core.transcription.TranscriptionRepository
import com.sildeag.sound2text.uicommon.models.UiTranscript
import com.sildeag.sound2text.uicommon.mappers.TranscriptUiMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
class TranscriptListViewModel(
    private val repo: TranscriptionRepository,
    private val mapper: TranscriptUiMapper
) {
    private val _items =
        MutableStateFlow<List<UiTranscript>>(emptyList())
    val items: StateFlow<List<UiTranscript>> = _items
    suspend fun loadAll() {
        val coreList = repo.getAll()
        val uiList = coreList.map { mapper.map(it) }
        _items.update { uiList }
    }
    suspend fun delete(id: String) {
        repo.delete(id)
        loadAll()
    }
}
