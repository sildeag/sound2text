package com.sildeag.sound2text.uicommon.navigation

import androidx.compose.runtime.Composable

@Composable
fun NavigationHost(
    route: String,
    home: @Composable () -> Unit,
    recording: @Composable () -> Unit,
    transcripts: @Composable () -> Unit,
    pdfWizard: @Composable () -> Unit
) {
    when (route) {
        "home" -> home()
        "recording" -> recording()
        "transcripts" -> transcripts()
        "pdfwizard" -> pdfWizard()
        else -> home()
    }
}