package com.sildeag.sound2text.pdfdesktop.itext

import pdf.FieldType
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
class ITextFormDiscoveryTest {
    @Test
    fun discoversFormsInDirectory() {
        val discovery = ITextFormDiscovery()
        val basePath = "src/jvmTest/resources/forms" // put sample PDFs here
        val forms = discovery.discoverForms(basePath)
        assertTrue(forms.isNotEmpty(), "Expected at least one form")
        val first = forms.first()
        assertTrue(first.fields.isNotEmpty(), "Expected fields in form")
    }
    @Test
    fun mapsFieldTypesCorrectly() {
        val discovery = ITextFormDiscovery()
        val basePath = "src/jvmTest/resources/forms/text_checkbox_dropdown.pdf"
        val forms = discovery.discoverForms(basePath)
        val fields = forms.first().fields
        // Adjust names/types to match your test PDF
        val textField = fields.first { it.name == "textField" }
        val checkbox = fields.first { it.name == "checkBox" }
        assertEquals(FieldType.TEXT, textField.type)
        assertEquals(FieldType.CHECKBOX, checkbox.type)
    }
}
