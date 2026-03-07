package com.sildeag.sound2text.service.note

import com.sildeag.sound2text.core.config.Environment
import com.sildeag.sound2text.di.TestModules
import com.sildeag.sound2text.test.KoinTestHarness
import org.junit.jupiter.api.Test

/*
class NoteProviderMockTest {
    @Test
    fun `tracks PDF generation calls`() {
        val mock = NoteProviderMock()
        mock.generatePDF("mock.pdf")
        assertEquals(listOf("Hello" to "file.pdf"), mock.calls)
    }
}
 */

class FullLifecycleTest : KoinTestHarness(
    environment = Environment.TEST,
    moduleOverrides = {
        includes(TestModules.fullStack)
        includes(TestModules.recognizerMock)
        includes(TestModules.pdfWriterMock)
    }
) {
class NoteMockTest : KoinTestHarness({
    single<NoteProvider> { NoteProviderMock(get(),get()) }
}) {
    @Test
    fun `should use mock note provider`() {
        val provider: NoteProvider = getKoin().get()
        assert(provider is NoteProviderMock)
    }
}
