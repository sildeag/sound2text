package com.sildeag.sound2text.uidesktop.service.note

import com.sildeag.sound2text.core.config.Environment
import com.sildeag.sound2text.uidesktop.di.TestModules
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

class FullLifecycleTest : com.sildeag.sound2text.test.KoinTestHarness(
    environment = Environment.TEST,
    moduleOverrides = {
        includes(_root_ide_package_.com.sildeag.sound2text.uidesktop.di.TestModules.fullStack)
        includes(_root_ide_package_.com.sildeag.sound2text.uidesktop.di.TestModules.recognizerMock)
        includes(_root_ide_package_.com.sildeag.sound2text.uidesktop.di.TestModules.pdfWriterMock)
    }
) {
class NoteMockTest : com.sildeag.sound2text.test.KoinTestHarness({
    single<NoteProvider> {
        _root_ide_package_.com.sildeag.sound2text.uidesktop.service.note.NoteProviderMock(
            get(),
            get()
        )
    }
}) {
    @Test
    fun `should use mock note provider`() {
        val provider: NoteProvider = getKoin().get()
        assert(provider is NoteProviderMock)
    }
}
