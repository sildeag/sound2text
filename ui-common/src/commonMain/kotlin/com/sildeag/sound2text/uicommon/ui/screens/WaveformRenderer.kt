package com.sildeag.sound2text.uicommon.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable

fun WaveformRenderer(
    amplitudes: List<Float>,
    modifier: Modifier = Modifier
) {
    // Create stable animation holders
    val animatables = remember(amplitudes.size) {
        List(amplitudes.size) { androidx.compose.animation.core.Animatable(0f) }
    }

    // Launch animations when amplitudes change
    amplitudes.forEachIndexed { i, amp ->
        LaunchedEffect(amp) {
            animatables[i].animateTo(
                targetValue = amp,
                animationSpec = tween(80)
            )
        }
    }

    Canvas(modifier = modifier) {
        val barWidth = size.width / (animatables.size.coerceAtLeast(1))
        val centerY = size.height / 2f

        animatables.forEachIndexed { i, anim ->
            val barHeight = anim.value * size.height
            val x = i * barWidth

            drawRoundRect(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF00E5FF),
                        Color(0xFF2979FF)
                    )
                ),
                topLeft = Offset(x, centerY - barHeight / 2),
                size = Size(barWidth * 0.8f, barHeight),
                cornerRadius = CornerRadius(6f, 6f)
            )
        }
    }
}
