package com.sildeag.sound2text.core.pdf

interface PdfFormEngine {
    /**
     * Returns the list of fields in the form.
     * This should match the fields in PdfFormDescriptor.
     */
    fun listFields(): List<PdfFieldDescriptor>
    /**
     * Fills a single field with text or a selected value.
     */
    fun fillField(name: String, value: String)
    /**
     * Saves the filled form to a new PDF file.
     */
    fun saveTo(path: String)
}