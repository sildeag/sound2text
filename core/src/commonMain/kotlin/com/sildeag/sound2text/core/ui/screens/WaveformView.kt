package com.sildeag.sound2text.core.ui.screens

import com.sildeag.sound2text.core.ui.state.WaveformState

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
