package com.sildeag.sound2text.uicommon

import androidx.compose.runtime.Composable
import com.sildeag.sound2text.uicommon.navigation.*
import com.sildeag.sound2text.uicommon.screens.*
import com.sildeag.sound2text.uicommon.pdf.PdfViewModel
import com.sildeag.sound2text.featurerecording.SttViewModel
import com.sildeag.sound2text.uicommon.screens.NoteEditorScreen
import com.sildeag.sound2text.uicommon.screens.SettingsScreen

@Composable
fun AppRoot(
    navigator: AppNavigator,
    sttViewModel: SttViewModel,
    pdfViewModel: PdfViewModel,
    renderPdfScreen: @Composable (String, Int, PdfViewModel) -> Unit
) {
    NavGraph(
        navigator = navigator,
        sttViewModel = sttViewModel,
        pdfViewModel = pdfViewModel,
        renderPdfScreen = renderPdfScreen,
        renderNotesList = {
            NotesListScreen(
                navigator = navigator,
                notes = listOf(
                    1L to "First Note",
                    2L to "Second Note"
                )
            )
        },
        renderNoteEditor = { id ->
            NoteEditorScreen(
                navigator = navigator,
                noteId = id
            )
        },
        renderSettings = {
            SettingsScreen()
        }
    )
}

/*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.sildeag.sound2text.appcommon.navigation.NavigationState
import com.sildeag.sound2text.uicommon.screens.*
import com.sildeag.sound2text.uicommon.theme.SharedTheme


@Composable
fun AppRoot(
    nav: NavigationState,
    onStartRecording: () -> Unit,
    onStopRecording: () -> Unit
) {
    SharedTheme {
        val currentScreen by nav.current.collectAsState()
        when (currentScreen) {
            is NavigationState.Screen.NotesList ->
                NotesListScreen(
                    onNoteSelected = { note ->
                        nav.openNoteDetail(note.id) },
                    onCreateNew = { nav.openNoteEditor(null) }
                )
            is NavigationState.Screen.NoteDetail -> {
                val screen = currentScreen as
                        NavigationState.Screen.NoteDetail
                NoteDetailScreen(
                    note = screen.note
                )
            }
            is NavigationState.Screen.NoteEditor -> {
                val screen = currentScreen as
                        NavigationState.Screen.NoteEditor
                NoteEditorScreen(
                    noteId = screen.noteId,
                    onDone = { nav.goBack() }
                )
            }
            is NavigationState.Screen.SoundToText ->
                Sound2TextScreen(
                    onBack = { nav.goBack() },
                    onStartRecording = onStartRecording,
                    onStopRecording = onStopRecording
                )
        }
    }
}
*/
