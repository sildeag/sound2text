package com.sildeag.sound2text.uicommon

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sildeag.sound2text.di.FeatureRegistry
@Composable
fun MainDashboardScreen(featureRegistry: FeatureRegistry) {
    var selectedTab by remember { mutableStateOf(0) }
    val features = featureRegistry.processors
    println("=== KOIN DEBUG: Found ${features} featureRegistry.processors ===")
    features.forEach { println(" -> processor: ${it::class.java.name}, features: ${it.title}") }

    Scaffold(
        bottomBar = {
            if (features.isNotEmpty()) {
                NavigationBar {
                    features.forEachIndexed { index, feature ->
                        NavigationBarItem(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            label = { Text(feature.title) },
                            icon = {}
                        )
                    }
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            if (features.isEmpty()) {
                Text("No features registered!", modifier = Modifier.padding(16.dp))
            } else {
                // Dynamically render the selected loosely-coupled feature view
                features[selectedTab].content()
            }
        }
    }
}
