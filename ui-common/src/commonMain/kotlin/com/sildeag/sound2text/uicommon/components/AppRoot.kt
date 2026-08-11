package com.sildeag.sound2text.uicommon.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.sildeag.sound2text.uicommon.navigation.NavigationHost
import com.sildeag.sound2text.uicommon.viewmodel.*
@Composable
fun AppRoot(
    navVm: NavigationViewModel,
    themeVm: ThemeViewModel,
    recordingVm: RecordingViewModel,
    transcriptVm: TranscriptListViewModel,
    pdfWizardVm: PdfWizardViewModel,
    sttVm: SttViewModel
) {
    val route by navVm.route.collectAsState()
    val theme by themeVm.state.collectAsState()
    val recordingState by recordingVm.state.collectAsState()
    val transcripts by transcriptVm.items.collectAsState()
    val wizardState by pdfWizardVm.state.collectAsState()
    val sttState by sttVm.state.collectAsState()
    NavigationHost(
        route = route,
        home = {
            HomeScreen(
                onRecording = { navVm.push("recording") },
                onTranscripts = { navVm.push("transcripts") },
                onPdfWizard = { navVm.push("pdfwizard") },
                onSettings = { navVm.push("settings") }
            )
        },
        recording = {
            RecordingScreen(
                state = recordingState,
                onStart = { recordingVm.start() },
                onStop = { recordingVm.stop() }
            )
        },
        transcripts = {
            TranscriptListView(
                items = transcripts,
                onSelect = { /* future: open transcript detail */ }
            )
        },
        pdfWizard = {
            PdfWizardScreen(
                state = wizardState,
                onSelectPdf = { /* platform file picker */ },
                onDiscoverFields = { pdfWizardVm.discoverFields() },
                onNextField = { pdfWizardVm.nextField() },
                onPrevField = { pdfWizardVm.previousField() },
                onStartRecording = { pdfWizardVm.startRecording() },
                onStopRecording = { pdfWizardVm.stopRecording() },
                onApplyStt = { pdfWizardVm.applySttResult() },
                onManualEntry = { pdfWizardVm.manualEntry(it) },
                onGeneratePdf = { pdfWizardVm.generatePdf() },
                onSavePdf = { pdfWizardVm.savePdf("output.pdf") }
            )
        }
    )
}