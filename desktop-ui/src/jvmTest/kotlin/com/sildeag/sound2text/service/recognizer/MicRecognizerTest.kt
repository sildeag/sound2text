package com.sildeag.sound2text.service.recognizer

import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.test.Test
import kotlin.test.assertEquals
class MicRecognizerTest {
    @Test
    fun `should simulate mic and speech events`() = runTest {
        val recognizer = MockMicRecognizer()
        val results = mutableListOf<String>()
        val job = launch {
            recognizer.recognizedText.collect { results.add(it) }
        }
        recognizer.simulateMicStart()
        assertEquals(true, recognizer.micState.value)
        recognizer.simulateSpeech("Hello Gordon")
        recognizer.simulateSpeech("Testing silence control")
        recognizer.simulateMicStop()
        assertEquals(false, recognizer.micState.value)
        assertEquals(listOf("Hello Gordon", "Testing silence control"),
            results)
            job.cancel()
    }
}