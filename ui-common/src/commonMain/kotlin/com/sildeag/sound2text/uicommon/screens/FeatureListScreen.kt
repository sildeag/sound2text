package com.sildeag.sound2text.uicommon.screens

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

enum class Feature {
    STT,
    PDF,
    HISTORY,
    SETTINGS,
    GENEALOGY,
    FORM,
    RECORDING
}

object FeatureRegistry {
    val features = listOf(
        Feature.STT,
        Feature.PDF,
        Feature.HISTORY,
        Feature.SETTINGS,
        Feature.GENEALOGY,
        Feature.FORM,
        Feature.RECORDING
    )
}

enum class Feature(
    val title: String,
    val route: String
) {
    STT("Speech To Text", "stt"),
    PDF("PDF Tools", "pdf"),
    HISTORY("History", "history"),
    SETTINGS("Settings", "settings")
}

@Composable
fun FeatureButton(
    feature: Feature,
    onClick: () -> Unit
)

@Composable
fun FeatureListScreen(
    onFeatureSelected: (Feature) -> Unit
) {
    Column {
        FeatureRegistry.features.forEach { feature ->
            FeatureButton(
                feature = feature,
                onClick = {
                    onFeatureSelected(feature)
                }
            )
        }
    }
}

@Composable
fun FeatureButton(
    feature: Feature,
    onClick: () -> Unit
) {
    Button(onClick = onClick) {
        Text(feature.title)
    }
}

@Composable
fun FeatureButton(
    feature: Feature
) {
    Button(
        onClick = {
            // Navigation goes here
        }
    ) {
        Text(feature.title)
    }
}
//GENEALOGY("Genealogy", "genealogy"),
//RECORDING("Recording", "recording"),
//FORM("Forms", "form")

@Composable
fun FeatureListScreen() {
    Column {
        FeatureRegistry.features.forEach { feature ->
            FeatureButton(feature)
        }
    }
}