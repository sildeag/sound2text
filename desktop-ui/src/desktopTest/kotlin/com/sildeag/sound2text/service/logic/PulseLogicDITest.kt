package com.sildeag.sound2text.service.logic

import com.sildeag.sound2text.di.PulseLogicParams
import com.sildeag.sound2text.di.loadEnvironmentModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.Test
import kotlin.test.assertEquals

class PulseLogicDITest : KoinTest {
    @Test
    fun `should inject PulseLogic with correct callbacks`() {
        // Arrange: Track callback invocations
        var recognizedText = ""
        var micState = false
        var pulseColor = ""
        val onText: (String) -> Unit = { recognizedText = it }
        val onMicState: (Boolean) -> Unit = { micState = it }
        val onPulseColor: (String) -> Unit = { pulseColor = it }
        startKoin {
            modules(loadEnvironmentModules())
        }

        val pulseParams = PulseLogicParams(
            onText = onText,
            onMic = onMicState,
            onPulse = { /* optional */ },
            onPulseUpdate = { /* optional */ },
            onPulseColor = onPulseColor,
            recognizerFactory = { get() },
            environment = "DEV"
        )

        val pulseLogic: PulseLogic = get {
            parametersOf(pulseParams)
        }

        // Simulate behavior
        pulseLogic.triggerText("Hello world")
        pulseLogic.triggerMic(true)
        pulseLogic.triggerPulseColor("blue")
        // Assert
        assertEquals("Hello world", recognizedText)
        assertEquals(true, micState)
        assertEquals("blue", pulseColor)

        stopKoin()

    }
}