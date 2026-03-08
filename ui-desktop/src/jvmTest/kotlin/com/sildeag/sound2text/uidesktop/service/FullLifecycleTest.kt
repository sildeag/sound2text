package com.sildeag.sound2text.uidesktop.service

import kotlinx.coroutines.test.runTest
import com.sildeag.sound2text.core.config.Environment
import com.sildeag.sound2text.di.SoundViewModelParams
import com.sildeag.sound2text.uidesktop.di.TestModules
import kotlin.test.Test
import org.koin.core.parameter.parametersOf
import com.sildeag.sound2text.test.KoinTestHarness
import com.sildeag.sound2text.`desktop-ui`.ui.SoundViewModel
import kotlinx.coroutines.delay
import org.koin.core.component.inject

class FullLifecycleTest : com.sildeag.sound2text.test.KoinTestHarness(
    environment = Environment.TEST,
    moduleOverrides = {
        includes(_root_ide_package_.com.sildeag.sound2text.uidesktop.di.TestModules.fullStack)
        includes(_root_ide_package_.com.sildeag.sound2text.uidesktop.di.TestModules.recognizerMock)
        includes(_root_ide_package_.com.sildeag.sound2text.uidesktop.di.TestModules.pdfWriterMock)
    }
) {
    @Test
    fun `should validate full app flow`() = runTest {
        val viewModel: SoundViewModel by inject {
            parametersOf(
                SoundViewModelParams(
                    onText = { println("Text: $it") },
                    onMic = { println("Mic: $it") },
                    onPulse = { println("Pulse: $it") },
                    onPulseColor = { println("Color: $it") },
                    onPulseUpdate = { println("Update: $it") },
                    environment = "TEST"
                )
            )
        }
        viewModel.toggleMic()
        delay(3000)
        viewModel.onFocusLost()
        viewModel.correctGrammar()
        viewModel.generatePDF("/tmp/test.pdf")
    }
}