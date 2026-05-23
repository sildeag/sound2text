package com.sildeag.sound2text.uicommon.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sildeag.sound2text.uicommon.ui.state.WaveformState

@Composable
fun WaveformView(state: WaveformState) {
    Canvas(modifier = Modifier.fillMaxWidth().height(80.dp)) {
        val barWidth = size.width / state.amplitudes.size
        state.amplitudes.forEachIndexed { i, amp ->
            val height = amp * size.height
            drawRect(
                color = Color.Green,
                topLeft = Offset(i * barWidth, size.height - height),
                size = Size(barWidth, height)
            )
        }
    }
}
