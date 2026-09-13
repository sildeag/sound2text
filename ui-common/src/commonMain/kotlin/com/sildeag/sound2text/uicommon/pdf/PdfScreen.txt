package com.sildeag.sound2text.uicommon.pdf

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun PdfScreen(viewModel: PdfViewModel) {
    val state by viewModel.state.collectAsState()
    Column {
        if (state.pages.isEmpty()) {
            Text("No PDF loaded")
        } else {
            LazyColumn {
                items(state.pages) { page ->
                    PdfPageView(page)
                }
            }
        }
    }
}

@Composable
fun PdfPageView(x0: Int) {
    TODO("Not yet implemented")
}