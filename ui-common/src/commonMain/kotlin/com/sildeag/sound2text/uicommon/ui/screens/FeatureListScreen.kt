package com.sildeag.sound2text.uicommon.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.sildeag.sound2text.uicommon.viewmodel.FeatureListViewModel

@Composable
fun FeatureListScreen(viewModel: FeatureListViewModel) {
    val features by viewModel.features.collectAsState()
    Column {
        features.forEach { feature ->
            FeatureButton(feature)
        }
    }
}