package com.sildeag.sound2text.androidui.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.sildeag.sound2text.android.viewmodel.AndroidSoundViewModel
import com.sildeag.sound2text.coreui.ui.Sound2TextScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun ComposeSoundScreen(
    viewModel: AndroidSoundViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    Sound2TextScreen(
        state = state,
        onEvent = viewModel::onEvent
    )
}
