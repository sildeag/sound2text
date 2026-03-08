package com.sildeag.sound2text.uidesktop.service.note

import com.sildeag.sound2text.core.config.Environment
import com.sildeag.sound2text.uidesktop.di.TestModules
import com.sildeag.sound2text.test.KoinTestHarness
import org.junit.jupiter.api.Test
import org.koin.test.inject
import kotlin.test.assertEquals

class NoteEditorImplTest : com.sildeag.sound2text.test.KoinTestHarness(
    environment = Environment.TEST,
    moduleOverrides = {
        includes(_root_ide_package_.com.sildeag.sound2text.uidesktop.di.TestModules.pdfWriterMock)
    }
) {
    private val editor: NoteEditorImpl by inject()
    @Test
    fun `appends and retrieves text`() {
        editor.append("Hello")
        assertEquals("Hello\n", editor.getText())
    }
}