package com.sildeag.sound2text.uicommon.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.sildeag.sound2text.uicommon.viewmodels.NavigationViewModel
@Composable
fun NavigationContainer(
    navVm: NavigationViewModel,
    home: @Composable () -> Unit,
    recording: @Composable () -> Unit,
    transcripts: @Composable () -> Unit,
    pdfWizard: @Composable () -> Unit,
    settings: @Composable () -> Unit
) {
    val route by navVm.route.collectAsState()
    NavigationHost(
        route = route,
        home = home,
        recording = recording,
        transcripts = transcripts,
        pdfWizard = pdfWizard
    )
}